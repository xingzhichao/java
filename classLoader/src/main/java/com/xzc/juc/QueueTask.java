package com.xzc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName QueueTask
 * @Description 排队任务
 * @Author zhichao.xing
 * @Date 2021/4/5 10:03
 * @Version 1.0
 **/
public class QueueTask implements Runnable {

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始排队买药");
            Thread.sleep(5000);
            System.out.println("排队成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            //可以看到子线程并没有因为调用latch.countDown而阻塞,会继续进行该做的工作,只是通知计数器-1,即完成了我们如上说的场景,只需要在所有进程都进行到某一节点后才会执行被阻塞的进程
            System.out.println("排队做自己的事");
        }
    }
}