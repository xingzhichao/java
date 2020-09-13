package com.xzc.java8;

import java.lang.reflect.Field;

/**
 * @ClassName IntegerCacheTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/9/6 15:21
 * @Version 1.0
 **/
public class IntegerCacheTest {
    /**
     * @Description -XX:AutoBoxCacheMax=20000
     * @Author xzc
     * @Date 16:51 2020/9/6
     * @return void
     **/
    public static void main(String[] args) {
        Class<?> declaredClass = Integer.class.getDeclaredClasses()[0];
        try {
            Field cache = declaredClass.getDeclaredField("cache");
            cache.setAccessible(true);
            Integer[] newCache = new Integer[0];
            try {
                newCache = (Integer[]) cache.get(declaredClass);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            newCache[132] = newCache[134];
            int a = 2;
            int b = a + a;
            System.out.printf("%d + %d = %d", a, a, b);

        } catch (NoSuchFieldException e) {

            e.printStackTrace();
        }

    }
}
