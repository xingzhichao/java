package com.xzc.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName FailFastTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/10/9 15:42
 * @Version 1.0
 **/
public class FailFastTest {

    @Test
    public  void test(){
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++ ) {
            list.add(i + "");
        }

        for (int i =0;i<7;i++){
            list.remove(i);
        }

      /*  Iterator<String> iterator = list.iterator();
        int i = 0 ;
        while(iterator.hasNext()) {
            if (i == 3) {
//                list.remove(3);
                iterator.remove();
            }
            System.out.println(iterator.next());
            i ++;
        }*/

    }
}
