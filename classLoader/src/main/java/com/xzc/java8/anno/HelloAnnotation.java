package com.xzc.java8.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName HelloAnnotation
 * @Description </></>
 * Annotation 可以在jdk包里面找到，它是所有注解的父接口
 * @Author zhichao.xing
 * @Date 2021/6/17 16:04
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnnotation {

    String say() default "Hi";

}

/**
 * @Description 所有的注解类型都继承自这个普通的接口（Annotation），
 * 有关这一点，你可以去反编译任意一个注解类，你会得到结果的。
 * <p>
 * 以下内容是反编译后的结果
 * @Author xzc
 * @Date 15:31 2021/8/13
 * @return
 **/
//F:\IdeaProjects\github\StudyByDay\classLoader\src\main\java\com\xzc\java8\anno>javap -c HelloAnnotation.class
//Compiled from "HelloAnnotation.java"
//public interface com.xzc.java8.anno.HelloAnnotation extends java.lang.annotation.Annotation {
//public abstract java.lang.String say();
//        }
//
//        F:\IdeaProjects\github\StudyByDay\classLoader\src\main\java\com\xzc\java8\anno>
