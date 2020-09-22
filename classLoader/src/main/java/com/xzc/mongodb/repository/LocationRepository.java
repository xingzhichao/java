package com.xzc.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void run() {
        Class<? extends MongoTemplate> aClass = mongoTemplate.getClass();
        System.out.println(aClass.getName());
    }

}
