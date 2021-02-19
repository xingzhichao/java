package com.xzc.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName FailFastTest
 * @Description
 * @Author zhichao.xing
 * @Date 2020/10/9 15:42
 * @Version 1.0
 * @see https://www.jianshu.com/p/c5b52927a61a
 **/
public class FailFastTest {

    /**
     * @return void
     * @Description 先增加  后删除，常见错误
     * @Author xzc
     * @Date 10:26 2021/2/19
     **/
    @Test
    public void testAddAndReduce() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        for (int i = 0; i < 7; i++) {
            list.remove(i);
        }
    }

    @Test
    public void testException() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0;
        //foreach循环等效于迭代器
        while (iterator.hasNext()) {
            if (i == 3) {
                // failfast 删除失败，报java.util.ConcurrentModificationException
                list.remove(3);
            }
            System.out.println(iterator.next());
            i++;
        }
    }

    /**
     * @return void
     * @Description * 但是，这个办法的有两个弊端
     * * 1.只能进行     iterator.remove操作，add、clear等Itr中没有。
     * * 2.而且只适用单线程环境。
     * @Author xzc
     * @Date 10:55 2021/2/19
     **/
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0;
        //foreach循环等效于迭代器
        while (iterator.hasNext()) {
            if (i == 3) {
                //  删除版本- 正常 -可以删除成功
                iterator.remove();
            }
            System.out.println(iterator.next());
            i++;
        }
    }
}
