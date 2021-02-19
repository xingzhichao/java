package com.xzc.java8.optional;

/**
 * @ClassName UserOptional
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/2/19 11:52
 * @Version 1.0
 **/
public class UserOptional {
    private String username;
    private Integer age;

    private UserOptional() {
    }

    /**
     * @return com.xzc.java8.optional.UserOptional
     * @Description 关于of方法，现在好像很流行，
     * 就是提供一个static方法，
     * 方法名称叫of，
     * 方法的返回值返回当前类，
     * 并且把构造函数设置为私有private，用静态of方法来代替构造函数
     * @Author xzc
     * @Date 11:53 2021/2/19
     **/
    public static UserOptional of() {
        return new UserOptional();
    }

    private UserOptional(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public static UserOptional of(String username, Integer age) {
        return new UserOptional(username, age);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserOptional{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
