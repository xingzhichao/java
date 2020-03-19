package com.xzc.javap;

import java.util.Date;

/**
 * @ClassName TestDate
 * @Description TODO javac javap
 * @Author zhichao.xing
 * @Date 2020/3/19 14:15
 * @Version 1.0
 **/
public class TestDate {
    /*
     https://www.jianshu.com/p/6a8997560b05
     JAVAC -g 生成class文件，然后通过javap命令对字节码进行反汇编：$ javap -c -l TestDate
    */

    private int count = 0;
    /**
     * @Description
     *
     *
     *
     * @Author xzc
     * @Date 14:17 2020/3/19
     * @return void
     **/
    public static void main(String[] args) {
        TestDate testDate = new TestDate();
        testDate.test1();

    }

    public void test1() {

        Date date = new Date();
        String name1 = "wangerbei";
        test2(date, name1);
        System.out.println(date + name1);

    }

    public void test2(Date dateP, String name2) {
        dateP = null;
        name2 = "zhangsan";
    }

    public void test3() {
        count++;
    }

    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        int c = a + 1;
    }
}
