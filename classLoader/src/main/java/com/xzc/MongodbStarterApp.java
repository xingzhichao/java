package com.xzc;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
/**
 * @Description 启动类
 * @Author xzc
 * @Date 10:25 2020/9/23
 * @return
 **/
@PropertySource({
        "classpath:application.yml",
        "classpath:conf.properties"
})
@SpringBootApplication
public class MongodbStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(MongodbStarterApp.class);
        System.out.println("项目启动成功!");
    }

    /**
     * @Description 实现RequestInterceptor接口: feign全局设置
     * @Author xzc
     * @Date 15:04 2021/7/29
     * @return
     **/
    @Configuration
    public class MyFeignRequestInterceptor  implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate requestTemplate) {
            requestTemplate.header("Token", "your token value");
        }
    }
}
