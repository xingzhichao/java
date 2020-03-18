package com.xzc.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication
public class WebsocketApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

}
