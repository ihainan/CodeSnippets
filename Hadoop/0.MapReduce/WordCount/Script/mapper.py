#!/usr/bin/env python2.7
# coding:utf-8
# Filename : mapper.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/20
# Description : The python implementation of mapper

import sys


def main():
	# 从 stdin 中读取一行
	for line in sys.stdin:
		# 分割
		for word in line[:-1].split(" "):
			# 输出
			print "%s\t1" % (word)

if __name__ == '__main__':
	main()
