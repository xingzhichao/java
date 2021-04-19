package com.xzc.juc.atomic;

import com.xzc.juc.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class AtomicStudentAgeUpdater {
    private String name;
    //    偏移地址与 数据类型有关 todo : 如何计算的？
    //    private volatile int age;//输出: valueOffset:--->12
    private volatile long age;//输出: valueOffset:--->16

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static final long valueOffset;

    static {
        try {
//            它提供了objectFieldOffset()方法用于获取某个字段相对Java对象的“起始地址”的偏移量，
//            也提供了getInt、getLong、getObject之类的方法可以使用前面获取的偏移量来访问某个Java对象的某个字段。
            valueOffset = unsafe.objectFieldOffset(AtomicStudentAgeUpdater.class.getDeclaredField("age"));
            System.out.println("valueOffset:--->" + valueOffset);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /**
     * 根据偏移量
     *
     * @param old
     * @param target
     */
    public void compareAndSwapAge(int old, int target) {
        unsafe.compareAndSwapInt(this, valueOffset, old, target);
    }

    public AtomicStudentAgeUpdater(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public long getAge() {
        return this.age;
    }

    public static void main(String[] args) {
        AtomicStudentAgeUpdater updater = new AtomicStudentAgeUpdater("杨过", 18);
        updater.compareAndSwapAge(18, 56);
        System.out.println("真实的杨过年龄---" + updater.getAge());
    }
}
