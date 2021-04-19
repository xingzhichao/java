package com.xzc.juc;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;

/**
 * 在对方线程调用exchange之前，另一个线程执行到exchange会阻塞 直到双方都调用exchange
 * http://zhangjiaheng.cn/blog/20190701/%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E5%AD%A6%E4%B9%A0-%E5%85%AD-%EF%BC%9AExchanger%E7%9A%84%E5%AD%A6%E4%B9%A0%E5%8F%8A%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF/
 */
public class ExchangerStudy {

    private static ArrayBlockingQueue<String> initialFillQueue
            = new ArrayBlockingQueue<>(5);
    private static ArrayBlockingQueue<String> initialEmptyQueue
            = new ArrayBlockingQueue<>(5);
    private static Exchanger<ArrayBlockingQueue<String>> exchanger
            = new Exchanger<>();

    /**
     * 填充缓存队列的线程
     */
    static class FillingRunnable implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = initialEmptyQueue;
            try {
                while (current != null) {
                    Thread.sleep(1000);
                    String str = UUID.randomUUID().toString();
                    try {
                        // ArrayBlockingQueue 的add 方法 会 throw new IllegalStateException("Queue full");
                        current.add(str);
                        System.out.println("生产了一个序列：" + str + ">>>>>加入到交换区");
                    } catch (IllegalStateException e) {
                        System.out.println("队列已满，换一个空的");
                        current = exchanger.exchange(current);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费缓存队列的线程
     */
    static class EmptyingRunnable implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = initialFillQueue;
            try {
                while (current != null) {
                    if (!current.isEmpty()) {
                        String str = current.poll();
                        System.out.println("消耗一个数列：" + str);
                    } else {
                        System.out.println("队列空了，换个满的");
                        current = exchanger.exchange(current);
                        System.out.println("换满的成功~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exchanger的使用
     * 下面的例子模拟一个队列中数据的交换使用的场景：
     * <p>
     * 线程A往队列中存入数据
     * 线程B从队列中消耗数据
     * 当线程A存满的时候
     * 才交换给线程B
     * 当线程B消耗完成之后才交换给线程A。
     * 线程A、B的生产和消耗的速率有可能不同
     * 对方线程调用exchange之前，另一个线程执行到exchange会阻塞
     */
    public static void main(String[] args) {
        new Thread(new FillingRunnable()).start();
        new Thread(new EmptyingRunnable()).start();

    }
}
