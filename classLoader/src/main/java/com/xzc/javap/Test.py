#!/usr/bin/python
#coding=UTF-8
import os,sys
from os.path import getsize
def lsdir(rootdir):
    list_dirs = os.walk(rootdir)
    size = 0
    for root,dirs,files in list_dirs:
        for name in files:
            size += getsize(os.path.join(root,name))
    return size

def haveDir(rootDir):
    list_dirs = os.walk(rootDir)
    for root,dirs,files in list_dirs:
        for dir in dirs:
            dirSize = lsdir(os.path.join(root,dir))
            dirSize = int(dirSize)
            dirSize = dirSize//1000000
            if dirSize > 500:
                print dir,":",dirSize,"M"
rootdir = r'C:\Windows'
haveDir(rootdir)