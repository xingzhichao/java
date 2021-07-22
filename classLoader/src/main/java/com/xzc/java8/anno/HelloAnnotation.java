package com.xzc.java8.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName HelloAnnotation
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/6/17 16:04
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnnotation {

    String say() default "Hi";

}