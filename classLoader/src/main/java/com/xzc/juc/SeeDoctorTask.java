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
        }
    }
}
