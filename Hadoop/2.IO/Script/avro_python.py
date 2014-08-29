#!/usr/bin/env python2.7
# coding:utf-8
# Filename : avro_python
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/22 11:00
# Description : A Python script used to practise the usage of Avro System
from avro import schema
from avro import io
from avro import datafile

import sys


def main():
    # 检测参数个数
    if len(sys.argv) != 3:
        sys.exit('Usage %s <Schema file> <Data_file>' % (sys.argv[0]))

    # 从 avsc 文件中读取模式
    schema_string = open(sys.argv[1], "r").read()

    # 打开 avro 文件
    avro_file = open(sys.argv[2], "wb")

    # 获取 DatumWriter 对象
    datum_writer = io.DatumWriter()

    # 解析模式
    schema_object = schema.parse(schema_string)

    # 获得 DataFileWriter 对象
    data_file_writer = datafile.DataFileWriter(avro_file, datum_writer, schema_object)

    # 从输入中赋值
    for line in sys.stdin:
        (left, right) = line[:-1].split(",")
        data_file_writer.append({'left':left, "right":right})

    # 关闭 DataFileWriter
    data_file_writer.close()

if __name__ == '__main__':
    main()