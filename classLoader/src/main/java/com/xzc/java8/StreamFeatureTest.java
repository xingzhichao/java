package com.xzc.java8;

import com.oracle.jrockit.jfr.EventInfo;
import com.xzc.java8.Invoice.Invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
        Consumer<String> consumerTest = abc -> System.out.println(abc);
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


//        List<Invoice> invoiceList=new ArrayList<>();
//        BigDecimal result = invoiceList.stream()
//                .map(Invoice::total)
//                .filter(Objects::nonNull)
//                .filter(i -> (i.getUnit_price() != null) && (i.getQuantity != null))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


//    Collectors的官方说明见：
//    官网文档
//
//    如果你对Function接口不是很了解，那么在使用这些方法时会很容易出错，下面进行简单的介绍：
//
//    Function<T, R>
//
//    T—函数的输入类型
//    R-函数的输出类型
//
//    也就是通过这个函数，可以将一个类型转换为另一个类型，比如下面的例子：
//
//    //定义一个function 输入是String类型，输出是 EventInfo 类型， EventInfo是一个类。
//    Function<String, EventInfo> times2 = fun -> { EventInfo a = new EventInfo(); a.setName(fun); return a;};
//
//    String[] testintStrings={"1","2","3","4"};
//
//    //将String 的Array转换成map,调用times2函数进行转换
//    Map<String,EventInfo> eventmap1=Stream.of(testintStrings).collect(Collectors.toMap(inputvalue->inputvalue, inputvalue->times2.apply(inputvalue)));
//
//    如果Collectors.toMap的转换过程很简单，比如输入和输出类型相同，则不需要另外定义Function,例如：
//
//    Map<String,String> eventmap2=Stream.of(testStrings).collect(Collectors.toMap(inputvalue->inputval
}
