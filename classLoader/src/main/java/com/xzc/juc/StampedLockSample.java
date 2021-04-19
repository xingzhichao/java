package com.xzc.juc;

import java.util.concurrent.locks.StampedLock;

/**
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 * 读写锁的一个改进版本
 * jdk8 提供的 StampedLock提供了一种乐观读锁的实现，
 * 这种乐观读锁类似于无锁的操作，完全不会阻塞写线程获取写锁，从而缓解读多写少时写线程“饥饿”现象
 */
public class StampedLockSample {
    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    void move(double xx, double yy) {
        long l = stampedLock.writeLock();//使用写锁- 独占操作
        try {
            x += xx;
            y += yy;
        } finally {
            stampedLock.unlockWrite(l);
        }
    }

    double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        //内存屏障    U.loadFence();
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
