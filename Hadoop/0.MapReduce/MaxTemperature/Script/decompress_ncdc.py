#!/usr/bin/env python2.7
# encoding:utf-8
# Filename : decompress_ncdc.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/25
# Description : A simple Python script used to decompress the compressed files from ncdc website
import os


def main():
    # 遍历 data 目录下的所有目录
    for dir_name in os.listdir("data"):
        # 解压缩所有文件
        tar_dir = "data/" + dir_name
        if len(os.listdir(tar_dir)) == 0:
            continue
        command = "gunzip " + tar_dir + "/*"
        os.system(command)

        # 读取目录下的所有文件中的数据，拼接，删除源文件
        data = ""
        for file_name in os.listdir(tar_dir):
            tar_file_dir = tar_dir + "/" + file_name
            fp = open(tar_file_dir, "r")
            data += fp.read()
            fp.close()
            os.remove(tar_file_dir)

        # 写入到新文件中
        if data != "":
            write_file_name = tar_dir + "/" + dir_name
            fp_write = open(write_file_name, "w+")
            fp_write.write(data)
            print "Write file " + write_file_name + " success"

if __name__ == '__main__':
    main()