package com.xzc.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierSample
 * @Description https://zhuanlan.zhihu.com/p/139020914
 * @Author zhichao.xing
 * @Date 2021/7/27 11:11
 * @Version 1.0
 **/
public class CyclicBarrierSample {
    /**
     * @return void
     * @Description 跑步场景吧, 十名运动员各自准备比赛, 需要等待所有运动员都准备好以后, 裁判才能说开始然后所有运动员一起跑,
     * @Author xzc
     * @Date 11:12 2021/7/27
     **/
    public static void main(String[] args) {
        //        可以看到所有线程在其他线程没有准备好之前都在被阻塞中,
//        等到所有线程都准备好了才继续执行
//        我们在创建CyclicBarrier对象时传入了一个方法[methodA],
//        当调用CyclicBarrier的await方法后,
//        当前线程会被阻塞   等到所有线程都调用了await方法之后 ，调用传入CyclicBarrier的方法[methodA],
//        然后让所有的被阻塞的线程一起运行
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("所有人都准备好了裁判开始了");
        });
        for (int i = 0; i < 10; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备" + System.currentTimeMillis());
                    Thread.sleep(1000 * times);

                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了" + System.currentTimeMillis());
                    /**
                     * @Description 此方法内部含有可重入锁
                     **/
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO: 2021/7/27 重置功能
//        cyclicBarrier.reset();
        int parties = cyclicBarrier.getParties();
        System.out.println(parties);
    }
}
