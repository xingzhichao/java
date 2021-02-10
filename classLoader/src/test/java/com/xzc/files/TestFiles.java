package com.xzc.files;

import org.junit.Test;

import java.io.File;

public class TestFiles {

    @Test
    public void test() {
        //1. 获取本机盘符
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.print("磁盘路径=" + roots[i].getPath() + "; ");
            System.out.print("磁盘总空间大小=" + roots[i].getTotalSpace() / 1024 / 1024 / 1024 + "; ");
//            System.out.print("使用磁盘空间大小="+roots[i].getUsableSpace()/1024/1024/1024+"; ");
            System.out.println("剩余磁盘空间大小=" + roots[i].getFreeSpace() / 1024 / 1024 / 1024);

            File file = new File(roots[i].getPath());
            getAllFilePath(file);
            break;
        }
    }


    public static void getAllFilePath(File dir) {
        File[] files = dir.listFiles();

        for (int i = 0; files!=null && i < files.length; i++) {
            if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.endsWith("jpg") ||
                        name.endsWith("jpeg") ||
                        name.endsWith("gif") ||
                        name.endsWith("png")
                ) {
                    System.out.println(files[i].getPath());
                }
            } else {
                //这里面用了递归的算fa
                if (files[i].isDirectory()) {
                    getAllFilePath(files[i]);
                }
            }
        }
    }
}
