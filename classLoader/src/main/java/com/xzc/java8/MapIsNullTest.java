package com.xzc.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @ClassName MapTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/4/29 10:10
 * @Version 1.0
 **/
public class MapIsNullTest {

    public static void main(String[] args) {
//        testKeyValue();
        testMapSize();
    }

    private static void testKeyValue() {
        // key,value 允许为空
        Map map = new HashMap(12);
        map.put(null, null);
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println("--");
        // key,value 都不允许为空
        map = new ConcurrentHashMap(12);
        map.put(null, null);
        int j=0;
        map.forEach((key, value) -> {
           new Thread(new Runnable() {
               @Override
               public void run() {
                   int j=0;
               }
           }).start();
            System.out.println(key + ":" + value);
        });
    }


    private static void testMapSize() {
//        Map map = new HashMap(12);
        Map map = new ConcurrentHashMap(12);
        for (int i = 0; i < 10000; i++) {
            map.put(i, "val" + i);
            System.out.println("map.size变为：" + map);
        }
        for (int i = 0; i < 10000; i++) {
            map.remove(i);
            System.out.println("map.size变为：" + map);
        }

    }
}
