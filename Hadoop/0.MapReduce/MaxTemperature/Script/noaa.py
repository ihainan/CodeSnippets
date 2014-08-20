#!/usr/bin/env python2.7
# coding:utf-8
# Filename : noaa.py
# Author : ihainan
# E-mail : ihainan72@gmail.com
# Website : http://www.ihainan.me
# Date : 2014/08/20
# Description : A simple Python script used to download temperature data from ncdc website

import os
import shutil
import urllib
from bs4 import BeautifulSoup


def main():
    # 建立目录
    if not os.path.isdir("data"):
        os.mkdir("data")

    # 定义年份
    year = 1955
    year_end = year + 50

    # 获取 链接对应文本内容
    url = "http://ftp3.ncdc.noaa.gov/pub/data/noaa/updates/"
    page = urllib.urlopen("file:index.html")
    page_data = page.read()
    print "read data from page success"

    # 循环
    while year < year_end:
        # 检测文件夹是否存在，存在则删除。
        if os.path.exists("data/" + str(year)):
            shutil.rmtree("data/" + str(year))
        os.mkdir("data/" + str(year))

        # 新建文件夹
        print "Create directory " + str(year) + " success"

        # 获取链接，下载
        soup = BeautifulSoup(page_data)
        for link in soup.find_all('a'):
            if link.getText().find(str(year) + '.gz') != -1:
                print "Start Download file " + link.getText()
                urllib.urlretrieve(url + link.getText(), "data/" + str(year) + "/" + link.getText())
                print "Download file " + link.getText() + " success"
        year += 1


if __name__ == "__main__":
    main()