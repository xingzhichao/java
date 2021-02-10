package com.xzc.spring.testcompentscan;

import com.xzc.spring.testcompentscan.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinationNames = ctx.getBeanDefinitionNames();
        for (String name:beanDefinationNames) {
            System.out.println("bean的定义信息:"+name);
        }

    }
}
