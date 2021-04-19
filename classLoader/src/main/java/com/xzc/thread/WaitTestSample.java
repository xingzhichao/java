package com.xzc.thread;

//https://www.cnblogs.com/skywang12345/p/3496716.html
public class WaitTestSample {
    /**
     * 是通过Object的wait(),  notify()来演示线程的休眠/唤醒功能。
     * 不加 锁不可以，等待和唤醒的 条件是 锁住同一资源的 线程。
     * Object.wait() – to suspend a thread
     * Object.notify() – to wake a thread up
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadA ta = new ThreadA("线程A");
        synchronized (ta) { // 通过synchronized(ta)获取“对象ta的同步锁”
            try {
                System.out.println(Thread.currentThread().getName() + " start ta");
                ta.start();

                System.out.println(Thread.currentThread().getName() + " block");
                ta.wait();    // 等待

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) { // 通过synchronized(this)获取“当前对象的同步锁”
                System.out.println(Thread.currentThread().getName() + " wakup others");
                notify();    // 唤醒“当前对象上的等待线程”
            }
        }
    }
}
