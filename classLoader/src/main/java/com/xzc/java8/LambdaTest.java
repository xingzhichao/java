package com.xzc.java8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @ClassName LambdaTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/29 12:54
 * @Version 1.0
 **/
public class LambdaTest {
    public static void main(String[] args) {
        AtomicInteger value= new AtomicInteger();
        IntStream.range(0,10).forEach(i-> value.getAndIncrement());
        try {
            Thread.sleep(1000 *120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
