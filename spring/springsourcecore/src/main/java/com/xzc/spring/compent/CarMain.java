package com.xzc.spring.compent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarMain {
    /**
     * 去容器中读取Bean
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("beans.xml");
        Object bean = classPathXmlApplicationContext.getBean("car_name");
        System.out.println(bean);
        System.out.println(bean.getClass());
    }
}
