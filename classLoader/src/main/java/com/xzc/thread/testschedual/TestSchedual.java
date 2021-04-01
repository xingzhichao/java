package com.xzc.thread.testschedual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestSchedual
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/3/31 15:40
 * @Version 1.0
 **/
public class TestSchedual {


    public static void main(String[] args) throws InterruptedException {

        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        // 周期性执行，每5秒执行一次
        for (int i = 0; i < 5; i++) {
            Task worker1 = new Task("task-" + i, 50000);
            scheduledThreadPool.scheduleAtFixedRate(worker1, 0, 5, TimeUnit.SECONDS);
        }

        Thread.sleep(10000);
        System.out.println("Shutting down executor...");
//        // 关闭线程池
//        scheduledThreadPool.shutdown();
//        boolean isDone;
//        // 等待线程池终止
//        do {
//            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
//            System.out.println("awaitTermination...");
//        } while (!isDone);
//
//        System.out.println("Finished all threads");
    }
}

class Task implements Runnable {

    private String name;
    private Integer count;

    public Task(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        List<SysDictionary> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SysDictionary sysDictionary = new SysDictionary();
            sysDictionary.setCreater("aa");
            sysDictionary.setCreateTime(new Date());
            sysDictionary.setDictionaryName("字典名" + this.toString());
            sysDictionary.setDictionaryValue("字典名2" + this.toString());
            sysDictionary.setIsDeleted(1);
            list.add(sysDictionary);
        }
        System.out.println("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + ", endTime = " + new Date());
    }

}