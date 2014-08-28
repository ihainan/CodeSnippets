#!/bin/sh
# Filename : convert_mode.sh
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/28 10:00
# Description : A shell script used to convert hadoop between standalone & pseudo-Distributed Mode.

CONF_PATH=$HADOOP_HOME/etc/hadoop
SBIN_PATH=$HADOOP_HOME/sbin
if [ "$1" == "Standalone" ]; then
	# 关闭 DFS 服务
	echo "正在关闭 DFS 服务"
	sh $SBIN_PATH/stop-dfs.sh

	# 拷贝配置文件
	echo "正在拷贝配置文件"
	cp $CONF_PATH/core-site-standalone.xml $CONF_PATH/core-site.xml
	cp $CONF_PATH/hdfs-site-standalone.xml $CONF_PATH/hdfs-site.xml

	echo "切换成 Standalone 模式成功"
else
	# 拷贝配置文件
	echo "正在拷贝配置文件"
	cp $CONF_PATH/core-site-pre.xml $CONF_PATH/core-site.xml
	cp $CONF_PATH/hdfs-site-pre.xml $CONF_PATH/hdfs-site.xml

	# 启动 DFS 服务
	echo "正在启动 DFS 服务"
	sh $SBIN_PATH/start-dfs.sh
	echo "切换成 Pseudo-Distributed 模式成功"
fi
