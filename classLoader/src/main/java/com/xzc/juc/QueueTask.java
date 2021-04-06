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
        }
    }
}