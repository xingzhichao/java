package com.xzc.java8;

/**
 * @ClassName HashTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/8/28 12:51
 * @Version 1.0
 **/
public class HashTest {

    public static void main(String[] args) {
        int xing = hash("xing123098jessenceu");
        System.out.println(xing);
    }

    static final int hash(Object key) {
        int h;
        h = key.hashCode();
        System.out.println(h);
        System.out.println(Integer.toBinaryString(h));
        System.out.println("length:"+Integer.toBinaryString(h).length());
        int i = h >>> 16;
        System.out.println("i1:"+i);
        System.out.println("i2:"+ (h>>16));
        System.out.println("i3:"+ (h>16));
        String string = Integer.toBinaryString(i);
        System.out.println("i.2:"+string);
        // TODO: 2020/8/28 2 转10
//        System.out.println("i.str:"+Integer.parseInt(string,10));
        
        return (key == null) ? 0 : (h = key.hashCode()) ^ i;
    }
}
