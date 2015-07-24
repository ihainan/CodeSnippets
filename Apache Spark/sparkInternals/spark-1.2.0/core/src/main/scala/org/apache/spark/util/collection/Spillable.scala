/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.util.collection

import org.apache.spark.Logging
import org.apache.spark.SparkEnv

/**
 * Spills contents of an in-memory collection to disk when the memory threshold
 * has been exceeded.
 */
private[spark] trait Spillable[C] extends Logging {
  /**
   * Spills the current in-memory collection to disk, and releases the memory.
   *
   * @param collection collection to spill to disk
   */
  protected def spill(collection: C): Unit  /* I: 见 ExternalSorter.scala */

  // Number of elements read from input since last spill
  protected def elementsRead: Long = _elementsRead

  // Called by subclasses every time a record is read
  // It's used for checking spilling frequency
  protected def addElementsRead(): Unit = { _elementsRead += 1 }

  // Memory manager that can be used to acquire/release memory
  private[this] val shuffleMemoryManager = SparkEnv.get.shuffleMemoryManager

  // Threshold for `elementsRead` before we start tracking this collection's memory usage
  private[this] val trackMemoryThreshold = 1000

  // Initial threshold for the size of a collection before we start tracking its memory usage
  // Exposed for testing
  private[this] val initialMemoryThreshold: Long =
    SparkEnv.get.conf.getLong("spark.shuffle.spill.initialMemoryThreshold", 5 * 1024 * 1024)

  // Threshold for this collection's size in bytes before we start tracking its memory usage
  // To avoid a large number of small spills, initialize this to a value orders of magnitude > 0
  private[this] var myMemoryThreshold = initialMemoryThreshold

  // Number of elements read from input since last spill
  private[this] var _elementsRead = 0L

  // Number of bytes spilled in total
  private[this] var _memoryBytesSpilled = 0L

  // Number of spills
  private[this] var _spillCount = 0

  /**
   * Spills the current in-memory collection to disk if needed. Attempts to acquire more
   * memory before spilling.
   *
   * @param collection collection to spill to disk
   * @param currentMemory estimated size of the collection in bytes
   * @return true if `collection` was spilled to disk; false otherwise
   */
  protected def maybeSpill(collection: C, currentMemory: Long): Boolean = {
    /*
      I: Spill 的条件：
        1. 上一次 Spill 之后，读取的元素个数得超过特定值
        2. 上一次 Spill 之后，读取的元素个数得是 32 的倍数
        3. 估计的 Map / Buffer 内存大小已经超过了阈值
     */
    if (elementsRead > trackMemoryThreshold && elementsRead % 32 == 0 &&
        currentMemory >= myMemoryThreshold) {
      /* I: 尝试去申请更大的内存空间 */
      // Claim up to double our current memory from the shuffle memory pool
      val amountToRequest = 2 * currentMemory - myMemoryThreshold
      val granted = shuffleMemoryManager.tryToAcquire(amountToRequest)
      myMemoryThreshold += granted
      /* I: 仍然无法满足，必须存储在磁盘中了 */
      if (myMemoryThreshold <= currentMemory) {
        // We were granted too little memory to grow further (either tryToAcquire returned 0,
        // or we already had more memory than myMemoryThreshold); spill the current collection
        _spillCount += 1
        logSpillage(currentMemory)

        spill(collection) /* 真正的 Spill 操作在此 */

        _elementsRead = 0 /* I: 重置 */
        // Keep track of spills, and release memory
        _memoryBytesSpilled += currentMemory  /* 记录释放内存总量 */
        releaseMemoryForThisThread()  /* I: 释放内存 */
        return true
      }
    }
    false
  }

  /**
   * @return number of bytes spilled in total
   */
  def memoryBytesSpilled: Long = _memoryBytesSpilled

  /**
   * Release our memory back to the shuffle pool so that other threads can grab it.
   */
  private def releaseMemoryForThisThread(): Unit = {
    // The amount we requested does not include the initial memory tracking threshold
    shuffleMemoryManager.release(myMemoryThreshold - initialMemoryThreshold)
    myMemoryThreshold = initialMemoryThreshold
  }

  /**
   * Prints a standard log message detailing spillage.
   *
   * @param size number of bytes spilled
   */
  @inline private def logSpillage(size: Long) {
    val threadId = Thread.currentThread().getId
    logInfo("Thread %d spilling in-memory map of %s to disk (%d time%s so far)"
      .format(threadId, org.apache.spark.util.Utils.bytesToString(size),
        _spillCount, if (_spillCount > 1) "s" else ""))
  }
}
