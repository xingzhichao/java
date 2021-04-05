package com.xzc.jvm;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SynchronizeTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/3/22 14:00
 * @Version 1.0
 **/
public class SynchronizeTest {
    //        Object object = new Object();
//        UnsafeUtils.getUnsafe().monitorEnter(object);
//        UnsafeUtils.getUnsafe().monitorExit(object);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        f();
    }

    /**
     * 可重入锁（递归锁）实战
     */
    public static void f() {
        if (atomicInteger.get() == 10) {
            System.out.println("end");
            return;
        }
        Object o = new Object();
        synchronized (o) {
            atomicInteger.getAndIncrement();
            System.out.println(atomicInteger.get());
            f();
        }
    }
}
