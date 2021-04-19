package com.xzc.juc;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

/**
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 */
public class HighAppSample {

    /**
     * 跨方法加锁解锁
     *
     * @param args
     */
    public static void main(String[] args) {

        Object o = new Object();
        add(o);
        multi(o);
    }

    public static void add(Object object) {
        /**
         * 直接获取报异常
         */
        Unsafe.getUnsafe().monitorEnter(object);
        Unsafe.getUnsafe().monitorExit(object);
        //通过反射获取单例对象theUnsafe。
        UnsafeUtils.getUnsafe().monitorEnter(object);
        System.out.println("+");
    }

    public static void multi(Object object) {
        System.out.println("*");
        UnsafeUtils.getUnsafe().monitorExit(object);
    }
}
