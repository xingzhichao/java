package com.xzc.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableScheduling

@ServletComponentScan
@SpringBootApplication
public class WebTimerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebTimerApplication.class, args);
    }

}
