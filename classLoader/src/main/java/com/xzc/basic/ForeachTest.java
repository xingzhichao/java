package com.xzc.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ForeachTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/19 15:47
 * @Version 1.0
 **/
public class ForeachTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");
        list.forEach(item -> {
            System.out.println(item);
        });
    }
}
