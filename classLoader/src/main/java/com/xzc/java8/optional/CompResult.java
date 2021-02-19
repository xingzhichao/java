package com.xzc.java8.optional;

/**
 * @ClassName CompResult
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/2/19 12:06
 * @Version 1.0
 **/
public class CompResult {

    private UserOptional userOptional;

    public UserOptional getUserOptional() {
        return userOptional;
    }

    public void setUserOptional(UserOptional userOptional) {
        this.userOptional = userOptional;
    }

    @Override
    public String toString() {
        return "CompResult{" +
                "userOptional=" + userOptional +
                '}';
    }
}
