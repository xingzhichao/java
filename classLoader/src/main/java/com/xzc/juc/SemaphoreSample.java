package com.xzc.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreSample
 * @Description 信号量，控制访问特定资源的线程数目
 * - 使用场景 ，资源访问，服务限流
 * - 分布式限流 ： redis+lua
 * @Author zhichao.xing
 * @Date 2021/4/1 13:10
 * @Version 1.0
 **/
public class SemaphoreSample {
    /**
     * CyclicBarrier 适用再多线程相互等待，直到到达一个屏障点。并且CyclicBarrier是可重用的。
     */
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    /**
     * @return void
     * @Description ，信号量 通过协调各个线程，以
     * 保证合理的使用公共资源。
     * 控制一组线程同时执行。
     * @Author xzc
     * @Date 15:56 2021/4/1
     **/
    public static void main(String[] args) {
        //todo： 数量为负数 的情况 需要研究一下
//        Semaphore semaphore = new Semaphore(-2);
        //默认 非公平锁   参数permits: 许可线程的数量
        Semaphore semaphore = new Semaphore(2);
        //公平锁
//        Semaphore semaphore2 = new Semaphore(2,true);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task(semaphore));
            thread.setName("xzc" + i);
            thread.start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        /**
         * @return void
         * @Description <>
         * <p>
         * 1.  若acquire 2 release 1 则 阻塞 永远等待释放
         * 2.  acquire 1 release 1  一次只有两个线程执行acquire ，只有线程进入release 方法后，才会有别的线程执行acquire();
         * 3.  todo     若acquire 2 release 2 ,打印日志 有点问题，执行到第三次循环，会有2个线程同时获取锁。
         * @Author xzc
         * @Date 15:37 2021/4/1
         **/
        @Override
        public void run() {
            try {
//                semaphore.acquire(2);
                semaphore.acquire(1);//获取许可 tryacquire 尝试获取许可
                System.out.println(Thread.currentThread().getName() + ":acquire at-" + System.currentTimeMillis());
                Thread.sleep(1000);

//                boolean b = semaphore.hasQueuedThreads();
//                System.out.println(Thread.currentThread().getName() + "等待队列里是否还存在等待线程" + b);
//                int queueLength = semaphore.getQueueLength();
//                System.out.println(Thread.currentThread().getName() + "获取等待队列里阻塞的线程数" + queueLength);

                System.out.println(Thread.currentThread().getName() + ":release at-" + System.currentTimeMillis());
                semaphore.release(1);
                //清空令牌把可用令牌数置为0，返回清空令牌的数量。
                // private volatile int state;  CAS 设置 state 为 0
//                semaphore.drainPermits();

//                int i = semaphore.availablePermits();// getstate 返回state
//                System.out.println("返回可用的令牌数量" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}