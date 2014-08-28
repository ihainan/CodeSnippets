#!/usr/bin/env python2.7
# encoding:utf-8
# Filename : mapper.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/28
# Description : The python implementation of mapper

import sys
import re


def main():
    # 标准输入读入每行
    for line in sys.stdin:
        # 对于每行，获取年、温度以及单位值
        (year, temperature, quality) = (line[15:19], line[87:92], line[92:93])

        # 判断温度和单位是否合法，合法则直接输出
        if year != "+9999" and re.match("[01459]", quality):
            print "%s\t%s" % (year, temperature)

if __name__ == '__main__':
    main()