package com.xzc.java8;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @ClassName FeatureTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/5/15 14:11
 * @Version 1.0
 **/
public class StreamFeatureTest {

    public static void main(String[] args) {
        //① 使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);

        System.out.println("********************");

        //② 使用lambda表达式，forEach方法需要的就是一个Consumer接口
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        //lambda表达式返回的就是一个Consumer接口
        Consumer<String> consumer1 = (s) -> System.out.println(s);
        Consumer<String> consumerTest=abc-> System.out.println(abc);
        stream.forEach(consumer1);
        System.out.println("********************");
        // stream has already been operated upon or closed 流只能被消费一次
//        stream.forEach(consumerTest);
        //更直接的方式
        stream = Stream.of("aaa-a", "bbb-b", "ddd-d");
        stream.forEach((s) -> System.out.println(s));
        System.out.println("********************");

        //③ 使用方法引用，方法引用也是一个consumer
        stream = Stream.of("aaaaaa", "bbbbbb", "dddddd", "cccccc", "ffffff");
        Consumer consumer2 = System.out::print;
        stream.forEach(consumer2);
        //更直接的方式
        //stream.forEach(System.out::println);
    }

}
