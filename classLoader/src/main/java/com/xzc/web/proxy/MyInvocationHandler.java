package com.xzc.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Description
 * AnnotationInvocationHandler  也是 InvocationHandler的实现类
 *
 * @Author zhichao.xing
 * @Date 2021/4/19 17:59
 * @Version 1.0
 **/
public class MyInvocationHandler implements InvocationHandler {
    /**
     * @Description 被代理对象，Object类型
     * @Author xzc
     * @Date 9:49 2021/6/18
     * @return
     **/
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备向数据库中插入数据");
        Object returnvalue = method.invoke(target, args);
        System.out.println("插入数据库成功");
        return returnvalue;
    }

}