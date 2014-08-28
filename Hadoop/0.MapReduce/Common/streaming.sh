#!/bin/sh

rm -rf output
hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-2.5.0.jar \
-input $1 \
-output $2 \
-mapper $3 \
-reducer $4
