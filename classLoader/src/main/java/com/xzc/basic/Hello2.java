package com.xzc.basic;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Hello
 * @Author zhichao.xing
 * @Date 2021/4/19 13:59
 * @Version 1.0
 **/
public class Hello2 {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("1", "2");
        stringList.add("3");
        new Thread() {
            @Override
            public void run() {
                System.out.println(stringList);
            }
        }.start();
        stringList.add("4");
    }
}
