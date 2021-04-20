package com.xzc.basic.integer;

/**
 * @ClassName IntegerCacheSample
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/20 10:26
 * @Version 1.0
 **/
public class IntegerCacheSample {
    /**
     * @return void
     * @Description vm :-XX:AutoBoxCacheMax=20000
     * @Author xzc
     * @Date 10:48 2021/4/20
     **/
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(1));

        Integer x = 1;
        Integer y = 1;
        System.out.println(String.format("1:%s", x == y));
        x = 127;
        y = 127;
        System.out.println(String.format("2:%s", x == y));
        x = 128;
        y = 128;
        boolean equals = x.equals(y);
        if (equals) {
            System.out.println(String.format("3:%s", x == y));
        } else {
            System.out.println("! equals");
        }
    }
}
