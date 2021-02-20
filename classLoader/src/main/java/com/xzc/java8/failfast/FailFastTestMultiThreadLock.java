package com.xzc.java8.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName FailFastTestMultiThread
 * @Description 多线程环境 - 有锁版本 synchronized
 * @Author zhichao.xing
 * @Date 2021/2/19 17:23
 * @Version 1.0
 **/
public class FailFastTestMultiThreadLock {
    static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        /**
         * @Description
         * 解决了多线程问题，但还是不能进行迭代add、clear等操作
         * @Author xzc
         * @Date 17:37 2021/2/19
         * @return void
         **/
        new Thread() {
            public void run() {
                Iterator<String> iterator = list.iterator();

                synchronized (list) {
                    while (iterator.hasNext()) {
                        System.out.println(Thread.currentThread().getName()
                                + ":" + iterator.next());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                Iterator<String> iterator = list.iterator();

                synchronized (list) {
                    while (iterator.hasNext()) {
                        String element = iterator.next();
                        System.out.println(Thread.currentThread().getName()
                                + ":" + element);
                        if (element.equals("c")) {
                            iterator.remove();
                        }

                    }
                }
            }

            ;
        }.start();
    }
}