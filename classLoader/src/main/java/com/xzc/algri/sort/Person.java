package com.xzc.algri.sort;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName Person
 * @Description "张三", "男", 22, 10000, "T2", "贵州遵义")
 * @Author zhichao.xing
 * @Date 2021/7/20 19:30
 * @Version 1.0
 **/
@Data
@Builder
public class Person {
    private String name;
    private String sex;
    private Integer age;
    private Integer salay;
    private String  level ;
    private String  address;

    public Person(String name, String sex, Integer age, Integer salay, String level, String address) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salay = salay;
        this.level = level;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", salay=" + salay +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
