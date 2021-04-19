package com.xzc.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoubleCheckLock {
    // volatile有序性
    private volatile static DoubleCheckLock instance = null;
//    private static DoubleCheckLock instance = null;

    /**
     * DCL 双重锁
     *
     * @return
     */
    public static DoubleCheckLock getInstance() {
        //第一次检测
        if (null == instance) {
            //同步
            synchronized (DoubleCheckLock.class) {
                if (null == instance) {
                    //多线程环境下可能会出现问题的地方
                    //todo : 模拟并发请求，并没有验证得到想要的结果
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DoubleCheckLock instance1 = getInstance();
        System.out.println(instance1);

//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                DoubleCheckLock instance1 = getInstance();
//                System.out.println(instance1);
//            }
//        };
//
//            executorService.execute(runnable);
//            executorService.execute(runnable);
//            executorService.execute(runnable);
//            executorService.execute(runnable);
//            executorService.execute(runnable);
//            executorService.execute(runnable);
//            executorService.execute(runnable);

//        executorService.shutdown();

//
//        for (int i = 0; i < 100; i++) {
//            final int num = i;
//            new Thread(new Runnable() {
//                public void run() {
//                    DoubleCheckLock instance = getInstance();
//                    System.out.println(instance);
//                }
//            }).start();
//        }
    }
}
