package com.xzc.algri;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @ClassName Random
 * @Description 生成随机数的多种方法
 * @Author zhichao.xing
 * @Date 2020/9/2 11:05
 * @Version 1.0
 **/
public class RandomTest {
    public static void main(String[] args) {
        double random = Math.random();
        System.out.println(random);
        double random1 = Math.random();
        System.out.println(random1);

        Random random2=new Random();
        System.out.println(random2.nextInt(100));


        try {
            SecureRandom secureRandom= SecureRandom.getInstance("SHA1PRNG");
            System.out.println(secureRandom.nextLong());
            System.out.println(secureRandom.nextLong());
            System.out.println(secureRandom.nextLong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
