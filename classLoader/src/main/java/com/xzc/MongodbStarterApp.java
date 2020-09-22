package com.xzc;

import com.xzc.mongodb.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(MongodbStarterApp.class);
        System.out.println("启动成功");
    }

}
