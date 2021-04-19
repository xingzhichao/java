package com.xzc.juc;

import java.util.concurrent.Exchanger;

/**
 * 当一个线程运行到exchange(同步点)方法时会阻塞，
 * 另外一个线程运行到exchange()时，两者会交换数据，然后执行后面的程序。
 * <p>
 *
 */
public class ExchangerSample {
    /**
     * 多线程环境，两两互相交换
     *
     * @param args
     */
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        for (int i = 0; i < 10; i++) {
            final Integer number = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this is thread" + Thread.currentThread().getName() + ",data is " + number);
                    try {
                        Thread.sleep(100);
                        Integer exchange = exchanger.exchange(number);
                        System.out.println("this is thread" + Thread.currentThread().getName()
                                + ",data is " + number + ",exchange data is " + exchange);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
