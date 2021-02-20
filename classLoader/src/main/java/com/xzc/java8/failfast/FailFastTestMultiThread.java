package com.xzc.java8.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName FailFastTestMultiThread
 * @Description 多线程环境 - 无锁版本
 * @Author zhichao.xing
 * @Date 2021/2/19 17:23
 * @Version 1.0
 **/
public class FailFastTestMultiThread {
    static List<String> list = new ArrayList<String>();

    /**
     * @return void
     * @Description 异常的原因很简单，一个线程修改了list的modCount导致另外一个线程迭代时modCount与该迭代器的expectedModCount不相等。
     * @Author xzc
     * @Date 17:27 2021/2/19
     **/
    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        new Thread() {
            @Override
            public void run() {
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()) {
                    System.out.println(Thread.currentThread().getName() + ":"
                            + iterator.next());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public  void run() {
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()) {
                    String element = iterator.next();
                    System.out.println(Thread.currentThread().getName() + ":"
                            + element);
                    if (element.equals("c")) {
                        iterator.remove();
                    }
                }
            }
        }.start();

    }
}
