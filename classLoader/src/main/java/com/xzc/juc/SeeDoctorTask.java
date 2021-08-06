package com.xzc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName SeeDoctorTask
 * @Description 看大夫任务
 * @Author zhichao.xing
 * @Date 2021/4/5 9:59
 * @Version 1.0
 **/
public class SeeDoctorTask implements Runnable {

    private CountDownLatch countDownLatch;

    public SeeDoctorTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始看医生");
            Thread.sleep(3000);
            System.out.println("看医生结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            /**
             * @Description countdownlatch 可以执行自己的事情
             * 可以看到子线程并没有因为调用latch.countDown而阻塞,会继续进行该做的工作,只是通知计数器-1,即完成了我们如上说的场景,只需要在所有进程都进行到某一节点后才会执行被阻塞的进程
             **/
            System.out.println("看医生做自己的事");
        }
    }
}
