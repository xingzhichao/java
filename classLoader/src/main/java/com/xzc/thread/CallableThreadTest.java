package com.xzc.thread;

import java.util.concurrent.*;

public class CallableThreadTest implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadTest callableThreadTest = new CallableThreadTest();
        FutureTask futureTask = new FutureTask(callableThreadTest);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "循环变量i=" + i);
            if (i == 20) {
                new Thread(futureTask, "有返回值的线程").start();
            }
        }
        System.out.println("返回值是" + futureTask.get());
    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        return i;
    }
}
