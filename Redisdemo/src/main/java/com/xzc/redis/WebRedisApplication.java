package com.xzc.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableScheduling

@ServletComponentScan
@SpringBootApplication
public class WebRedisApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebRedisApplication.class, args);
    }

}
