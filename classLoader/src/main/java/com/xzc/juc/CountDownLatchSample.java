package com.xzc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchSample
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/5 9:58
 * @Version 1.0
 **/
public class CountDownLatchSample {

    /**
     * @return void
     * @Description CountDownLatch（同步工具类） 允许一个或多个线程等待其他线程完成操作。
     * CountDownLatch 时，需要指定一个整数值，此值是线程将要等待的操作数。
     * 当某个线程为了要执行这些草作而等待时，需要调用 await 方法。
     * await 方法让线程进入休眠状态直到所有等待的操作完成为止。
     * 当等待的某个操作执行完成，它使用 countDown 方法来减少 CountDownLatch 类的内部计数器。
     * 当内部计数器递减为 0 时， CountDownLatch 会唤醒所有调用 await 方法而休眠的线程们。
     * <p>
     * 场景：
     * 并行计算
     * 依赖启动
     * CountDownLatch 是一次性的，只能通过构造方法设置初始计数量，计数完了无法进行复位，不能达到复用
     * @Author xzc
     * @Date 17:19 2021/4/5
     **/
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new QueueTask(countDownLatch)).start();
        new Thread(new SeeDoctorTask(countDownLatch)).start();

        /**
         * @Description 查询state是否等于0 ，若等于0 继续执行下一行代码，若不等于0 ，则执行共享中断模式
         * @Author xzc
         * @Date 10:29 2021/4/5
         * @return void
         **/
        countDownLatch.await();
        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new SeeDoctorTask(countDownLatch)).start();
        System.out.println("over - " + (System.currentTimeMillis() - now));
    }
}
