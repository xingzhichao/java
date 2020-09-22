package com.xzc.mongodb.entity;

import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class Person {
    private String id;
    private String name;
    private int age;
    //必须强制命名为location,否则mongTemplate不识别,会报错,还有就是point的包别导错了.
    private Point location;


    public Person(String name, int age, Point location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }
}
