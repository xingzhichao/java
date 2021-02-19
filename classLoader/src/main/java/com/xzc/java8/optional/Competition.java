package com.xzc.java8.optional;

/**
 * @ClassName Competition
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/2/19 12:06
 * @Version 1.0
 **/
public class Competition {
    private CompResult compResult;

    public CompResult getCompResult() {
        return compResult;
    }

    public void setCompResult(CompResult compResult) {
        this.compResult = compResult;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "compResult=" + compResult +
                '}';
    }
}
