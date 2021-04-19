package com.xzc.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (01) BoundedBuffer 是容量为5的缓冲，缓冲中存储的是Object对象，支持多线程的读/写缓冲。
 * 多个线程操作“一个BoundedBuffer对象”时，
 * 它们通过互斥锁lock对缓冲区items进行互斥访问；
 * 而且同一个BoundedBuffer对象下的全部线程共用“notFull”和“notEmpty”这两个Condition。
 * notFull用于控制写缓冲，notEmpty用于控制读缓冲。
 * 当缓冲已满的时候，调用put的线程会执行notFull.await()进行等待；
 * 当缓冲区不是满的状态时，就将对象添加到缓冲区并将缓冲区的容量count+1，
 * 最后，调用notEmpty.signal()缓冲notEmpty上的等待线程(调用notEmpty.await的线程)。
 * 简言之，
 * notFull控制“缓冲区的写入”，当往缓冲区写入数据之后会唤醒notEmpty上的等待线程。
 * 同理，
 * notEmpty控制“缓冲区的读取”，当读取了缓冲区数据之后会唤醒notFull上的等待线程。
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();    //获取锁
        try {
            // 如果“缓冲已满”，则等待；直到“缓冲”不是满的，才将x添加到缓冲中。
            while (count == items.length)//可以不用等待，但是池子是有大小限制的
                notFull.await();
            // 将x添加到缓冲中
            items[putptr] = x;
            // 将“put统计数putptr+1”；如果“缓冲已满”，则设putptr为0。
            if (++putptr == items.length) putptr = 0;
            // 将“缓冲”数量+1
            ++count;
            // 唤醒take线程，因为take线程通过notEmpty.await()等待
            notEmpty.signal();
            // 打印写入的数据
            System.out.println(Thread.currentThread().getName() + " put  " + (Integer) x);
        } finally {
            lock.unlock();    // 释放锁
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();    //获取锁
        try {
            // 如果“缓冲为空”，则等待；直到“缓冲”不为空，才将x从缓冲中取出。
            while (count == 0)
                notEmpty.await();
            // 将x从缓冲中取出
            Object x = items[takeptr];
            // 将“take统计数takeptr+1”；如果“缓冲为空”，则设takeptr为0。
            if (++takeptr == items.length) takeptr = 0;
            // 将“缓冲”数量-1
            --count;
            // 唤醒put线程，因为put线程通过notFull.await()等待
            notFull.signal();
            // 打印取出的数据
            System.out.println(Thread.currentThread().getName() + " take " + (Integer) x);
            return x;
        } finally {
            lock.unlock();    // 释放锁
        }
    }
}
