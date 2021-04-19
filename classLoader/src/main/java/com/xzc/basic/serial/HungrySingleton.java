package com.xzc.basic.serial;

import java.io.Serializable;

/**
 * @ClassName HungrySingleton
 * @Description TODO https://www.jianshu.com/p/d6fef5b9fbe5
 * @Author zhichao.xing
 * @Date 2021/4/19 16:36
 * @Version 1.0
 **/
public class HungrySingleton implements Serializable {
    private HungrySingleton() {
    }

    private static final HungrySingleton hungry = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return hungry;
    }

    /**
     * @return
     * @Description 我们添加的readResolve()方法
     * <p>
     * readResolve()中返回的对象
     * 直接替换
     * 在反序列化过程中创建的对象，
     * 而被创建的对象则会被垃圾回收掉
     * @Author xzc
     * @Date 16:46 2021/4/19
     **/
    private Object readResolve() {
        return hungry;
    }
}
