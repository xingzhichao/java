package com.xzc.juc;

import java.util.concurrent.TimeUnit;

/**
 * todo 想验证一下可见性，还是不太好验证。
 */
public class VolatileSample {
    /**
     * 这个可以，测试线程与主线程之间可以看到 可见性。
     * ① 可见性/一致性
     * ② 原子性 ：对任意单个 volatile 变量的读/写具有原子性，
     * 但类似于 volatile++ 这种复合操作 不具有原子性 。
     *
     * @param args
     */
    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " com in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + " change number to " + mydata.number);
        }, "测试线程").start();
        // 用于判断number的值有没有改变,此线程为main线程
        while (mydata.number == 0) {

        }
        // 主线程
        System.out.println(Thread.currentThread().getName() + " end");
    }

    static class Mydata {
        // 主线程不可以感知到
        int number = 0;
        // 主线程可以感知到
//        volatile int number = 0;

        public void addTo60() {
            this.number = 60;
        }
    }

    //失败版本
//    volatile static Boolean flag = true;
//
//    public static void main(String[] args) throws InterruptedException {
//        Integer i = 1;
//        new Thread(new TaskVolatileChange()).start();
//        while (flag) {
//            System.out.println("--" + i++);
//            Thread.sleep(100);
//        }
//        System.out.println("end....");
//    }
//
//    static class TaskVolatileChange implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            flag = false;
//            System.out.println("修改成功");
//        }
//    }
}
