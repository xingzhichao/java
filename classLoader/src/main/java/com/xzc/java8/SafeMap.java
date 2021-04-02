package com.xzc.java8;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @ClassName SafeMap
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/2 14:53
 * @Version 1.0
 **/
public class SafeMap {
    public static void main(String[] args) {
        WeakHashMap map=new WeakHashMap();
        Map map1 = Collections.synchronizedMap(map);

    }
}
