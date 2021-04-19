package com.xzc.basic.serial;

import lombok.Cleanup;

import java.io.*;

/**
 * @ClassName Client
 * @Description 在IO流的学习中，每次都要在finally里面关闭资源，
 * 是不是很让人头疼？那么有没有好的方法去生成这样的重复代码。
 * 方法有两种：
 * 一种
 * 是使用Lombok的@Cleanup，
 * 另一种
 * 是使用jdk1.7+的try-with-resources语法糖
 * @Author zhichao.xing
 * @Date 2021/4/19 16:37
 * @Version 1.0
 **/
public class Client {
    /**
     * @return void
     * @Description 从运行结果可以看出，序列化破坏了单例，产生了多个实例。
     * <p>
     * 那我们如何解决呢？
     * 在HungrySingleton 类中添加 readResolve()方法就可以完美解决
     * @Author xzc
     * @Date 16:41 2021/4/19
     **/
    public static void main(String[] args) {
        HungrySingleton s1 = HungrySingleton.getInstance();
        HungrySingleton s2 = null;
        try {
            // 将s1序列化到磁盘
            FileOutputStream fos = new FileOutputStream("a.obj");
            @Cleanup
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);

            oos.flush();

            @Cleanup
            FileInputStream fis = new FileInputStream("a.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            // 从磁盘反序列化
            s2 = (HungrySingleton) ois.readObject();
            System.out.println(s1 == s2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
