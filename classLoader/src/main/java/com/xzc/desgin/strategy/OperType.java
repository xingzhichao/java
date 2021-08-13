package com.xzc.desgin.strategy;

import com.xzc.desgin.strategy.impl.OperationAdd;
import com.xzc.desgin.strategy.impl.OperationSub;

/**
 * @Description 枚举类定义（OperType）
 * @Author xzc
 * @Date 14:35 2021/8/13
 * @return
 **/
public enum OperType {

    /**
     * @Description 相加
     * @Author xzc
     * @Date 14:36 2021/8/13
     * @return
     **/
    ADD(1, OperationAdd.getInstance()),

    /**
     * @Description 相减
     * @Author xzc
     * @Date 14:35 2021/8/13
     * @return
     **/
    SUB(2, OperationSub.getInstance());

    /**
     * @Description code
     * @Author xzc
     * @Date 14:35 2021/8/13
     * @return
     **/
    private Integer code;
    /**
     * @Description 实例- 具体的实现类
     * @Author xzc
     * @Date 14:35 2021/8/13
     * @return
     **/
    private SuperInterface option;

    private OperType(Integer code, SuperInterface option) {
        this.setCode(code);
        this.setOption(option);
    }

    public SuperInterface getOption() {
        return option;
    }

    public void setOption(SuperInterface option) {
        this.option = option;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
