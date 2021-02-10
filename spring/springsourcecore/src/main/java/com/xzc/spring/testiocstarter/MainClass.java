package com.xzc.spring.testiocstarter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(ctx.getBean(TulingService.class));
        System.out.println(ctx.getBean("tlDataSource",TulingDataSource.class));
    }
}
