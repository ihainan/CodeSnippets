#!/usr/bin/env python2.7
# coding:utf-8
# Filename : reducer.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/20
# Description : The python implementation of reducer

import sys


def main():
	(last_key, return_value) = (None, 0)
	for line in sys.stdin:	
		(key, value) = line[:-1].split("\t")
		if last_key and last_key != key:
			print "%s\t%d" % (last_key, return_value)
			(last_key, return_value) = (key, 1)
		else:
			(last_key, return_value) = (key, return_value + 1)

if __name__ == '__main__':
	main()
