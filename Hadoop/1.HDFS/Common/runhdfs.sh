#!/bin/sh
# Filename : runhdfs.sh
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/20 07:00
# Description : A shell script used to compile & run Java program for HDFS

javaFileName=$1
jarFileName=$2
argumens="$3 $4 $5"
echo "hadoop com.sun.tools.javac.Main $javaFileName.java && jar cf $jarFileName.jar $javaFileName*.class && rm -rf output && hadoop jar $jarFileName.jar $javaFileName $argumens"
hadoop com.sun.tools.javac.Main $javaFileName.java && jar cf $jarFileName.jar $javaFileName*.class && rm -rf output && hadoop jar $jarFileName.jar $javaFileName $argumens
