package com.xzc.basic;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @ClassName ArrayDequeSample
 * @Description 循环数组
 * @Author zhichao.xing
 * @Date 2021/4/25 17:18
 * @Version 1.0
 **/
public class ArrayDequeSample {

    /**
     * ArrayDeque 采用了循环数组的方式来完成双端队列的功能。 用两个 int 的
     * head、 tail 分别表示队列的头部下标、尾部下标。循环是通过表达式
     * (tail = (tail + 1) & (elements.length - 1))实现的。
     * <p>
     * 1. 数组的容量为 2 的次方，每次新增 1 倍。（如果保证 2 的次方，见 hashmap
     * 部分）
     * 2. 非线程安全的，不支持并发访问和修改。
     * 3. 支持 fast-fail。
     * 4. 作为栈使用的话比栈要快。
     * 5. 当队列使用比 LinkedList 要快。
     * 6. null 元素被禁止使用。
     **/
    public static void main(String[] args) {
        ArrayDeque arrayDeque=new ArrayDeque(Arrays.asList("4"));
        arrayDeque.addFirst("1");
        arrayDeque.addLast("9");
        System.out.println(JSON.toJSONString(arrayDeque));
    }
}
