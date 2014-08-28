#!/usr/bin/env python2.7
# encoding:utf-8
# Filename : mapper.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/28
# Description : The python implementation of reducer
import sys


def main():
    # 初始化 final_key, final_value
    (final_key, final_value) = (None, -10000)

    # 对于标准输入，读入每一行，获取 key, value
    for line in sys.stdin:
        (key, value) = (line[:-1].split("\t")[0], int(line[:-1].split("\t")[1]))

        # 如果 final_key 不为 None 并且 final_key != key，则输出并重置 final_key 与 final_value
        if final_key != None and key != final_key:
            print "%s\t%s" % (final_key, final_value)
            (final_key, final_value) = (None, -10000)

        # 否则替换 final_key 和 final_value
        else:
            (final_key, final_value) = (key, max(final_value, value))

    if final_key:
        print "%s\t%s" % (final_key, final_value)

if __name__ == '__main__':
    main()