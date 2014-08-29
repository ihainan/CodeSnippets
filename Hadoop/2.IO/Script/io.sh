#!/bin/sh
# Filename : io.sh
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/21 11:00
# Description : A shell script used to compile & run Java program for Hadoop IO

javaFileName=$1
jarFileName=$2
argumens="$3 $4 $5"
echo "hadoop com.sun.tools.javac.Main $javaFileName.java && jar cf $jarFileName.jar $javaFileName*.class && rm -rf output && hadoop jar $jarFileName.jar $javaFileName $argumens"

# for StreamCompressor.java
hadoop com.sun.tools.javac.Main $javaFileName.java && jar cf $jarFileName.jar $javaFileName*.class && rm -rf output
echo "Hello World" | hadoop jar $jarFileName.jar $javaFileName $argumens > test2.gzip

# hadoop com.sun.tools.javac.Main $javaFileName.java && jar cf $jarFileName.jar $javaFileName*.class && rm -rf output && hadoop jar $jarFileName.jar $javaFileName $argumens
