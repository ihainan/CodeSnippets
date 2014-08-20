#!/bin/sh
# Filename : mapreduce.sh
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/20 07:00
# Description : A shell script used to compile & run Java program for MapReduce

javaFileName=$1
jarFileName=$2
echo "hadoop com.sun.tools.javac.Main $1*.java && jar cf $2.jar $1*.class && rm -rf output && hadoop jar $2.jar $1 input output"
hadoop com.sun.tools.javac.Main $1*.java && jar cf $2.jar $1*.class && rm -rf output && hadoop jar $2.jar $1 input output
