package com.xzc.java8.failfast;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName FailFastTestMultiThread
 * @Description 多线程环境
 * @Author zhichao.xing
 * @Date 2021/2/19 17:23
 * @Version 1.0
 **/
public class FailFastTestMultiThreadLock2 {
    static List<String> list = new CopyOnWriteArrayList<String>();

    /**
     * @return void
     * @Description 采用CopyOnWriteArrayList，解决了多线程问题，同时可以add、clear等操作
     * @Author xzc
     * @Date 17:39 2021/2/19
     **/
    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        new Thread() {
            public void run() {
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()) {
                    System.out.println(Thread.currentThread().getName()
                            + ":" + iterator.next());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            ;
        }.start();

        new Thread() {
            //            public synchronized void run() {
            public void run() {
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()) {
                    String element = iterator.next();
                    System.out.println(Thread.currentThread().getName()
                            + ":" + element);
                    if (element.equals("c")) {
                        list.remove(element);
                    }
                    list.add("i");
                }
            }
        }.start();

    }
}