package com.xzc.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName ThreadTest
 * @Description java线程是内核级线程（KLT）
 * @Author zhichao.xing
 * @Date 2021/2/26 16:25
 * @Version 1.0
 **/
public class ThreadTest {

    /**
     * 并发编程之JMM&synchronized&volatile详解
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // demo1  验证 是用户级线程 还是内核级别线程
                    //                    System.out.println(Thread.currentThread().getName() + "正在执行任务");
//                    while (true) {
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    // demo2  配合验证thread.join
//                    System.out.println(Thread.currentThread().getName() + "执行完毕");

                    //demo3 配合验证 阻塞态
//                    "Thread-4" #15 prio=5 os_prio=0 tid=0x000000001dc16800 nid=0x1fc4 waiting for monitor entry [0x000000001f1bf000]
//                      java.lang.Thread.State: BLOCKED (on object monitor)
//                      at com.xzc.thread.ThreadTest$1.run(ThreadTest.java:38)
//                          - waiting to lock <merged>(a java.lang.Class for com.xzc.thread.ThreadTest)
//                          at java.lang.Thread.run(Thread.java:748)

                    synchronized (ThreadTest.class) {
//                    synchronized (this) {// run方法中的this 是阻塞不到其他线程的 ，每次都是new的，要是用全局的对象来锁定才可以验证
                        System.out.println(Thread.currentThread().getName() + "执行zhong");
                        while (true) {

                        }
                    }
                }
            });
            thread.start();
            //见下面注释
//            thread.join();
            /**
             * main 线程变成waiting 状态 ，
             * 并非 网上说的：park开头的方法来阻塞当前线程，unpark来唤醒被阻塞的线程。
             */
//            LockSupport.park();
        }
        System.out.println("main 执行完毕");
    }

    public void test() throws InterruptedException {
        //当前线程进入等待状态，直到  被another thread 唤醒notify  notifyall
        Object object = new Object();
        object.wait();//
        Thread thread = new Thread();
        /**
         *底层调用的是object.wait会出让锁，而 sleep() 会一直保持锁。
         * join方法必须在线程start方法调用之后调用才有意义。
         * 这个也很容易理解：如果一个线程都没有start，那它也就无法同步了。
         *
         * Waits for this thread to die.
         * 主线程等待 当前线程死亡 之后继续执行后续逻辑
         */
        thread.join();
    }
}
