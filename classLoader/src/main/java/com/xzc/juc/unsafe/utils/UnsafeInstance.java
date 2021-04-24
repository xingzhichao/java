package com.xzc.juc.unsafe.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ：图灵-杨过
 * @date：2019/7/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class UnsafeInstance {

    //
    private UnsafeInstance() {

    }

    /**
     * 反射获取unsafe对象
     *
     * @return
     */
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
