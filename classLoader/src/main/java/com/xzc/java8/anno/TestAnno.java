package com.xzc.java8.anno;

import java.util.*;

/**
 * @ClassName Test
 * @Description javap -verbose
 * @Author zhichao.xing
 * @Date 2021/6/17 16:04
 * @Version 1.0
 **/
@HelloAnnotation(say = "Do it!")
public class TestAnno {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        SortedMap sortedMap = new TreeMap(properties);
        Set set = sortedMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = properties.getProperty(key);
            System.out.println(key+"=====>"+value);
        }

//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取TestMain类上的注解对象
        HelloAnnotation annotation = TestAnno.class.getAnnotation(HelloAnnotation.class);
        //调用注解对象的say方法，并打印到控制台
        System.out.println(annotation.say());
    }

}
