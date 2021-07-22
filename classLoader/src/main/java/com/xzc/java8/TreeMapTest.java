package com.xzc.java8;

import java.util.TreeMap;

/**
 * @ClassName TreeMapTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/10 17:18
 * @Version 1.0
 **/
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        System.out.println("Checking floor entry for 6");
        /**
         * @Description floorEntry(K key) 方法用来返回与最大键小于或等于给定的键，或者如果不存在这样的键，null关联的键 - 值映射。
         * key-- 这是要匹配的键。
         **/
        System.out.println("Value is: " + treemap.floorEntry(4));
    }
}
