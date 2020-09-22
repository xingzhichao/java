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
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}
