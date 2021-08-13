package com.xzc.desgin.strategy.impl;

/**
 * @ClassName OperationSub
 * @Description 定义业务具体实现，此处借用加减操作进行封装（OperationSub）
 * @Author zhichao.xing
 * @Date 2021/8/13 14:30
 * @Version 1.0
 **/

import com.xzc.desgin.strategy.SuperInterface;

import java.util.List;

public class OperationSub implements SuperInterface {

    private OperationSub() {
    }

    private static OperationSub sub = null;

    public static OperationSub getInstance() {
        if (sub == null) {
            synchronized (OperationSub.class) {
                sub = new OperationSub();
            }
        }
        return sub;
    }

    @Override
    public Object doOperation(List objs) {
        double First = Double.valueOf(objs.get(0).toString());
        int i = 0;
        for (Object d : objs) {
            if (i == 0) {
                i = 1;
                continue;
            }
            double temp = Double.valueOf(d.toString());
            First -= temp;
        }
        return First;
    }

}
