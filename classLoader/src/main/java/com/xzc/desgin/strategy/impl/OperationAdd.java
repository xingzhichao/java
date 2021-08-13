package com.xzc.desgin.strategy.impl;

import com.xzc.desgin.strategy.SuperInterface;

import java.util.List;

/**
 * @ClassName OperationAdd
 * @Description 定义业务具体实现，此处借用加减操作进行封装（OperationAdd）
 * @Author zhichao.xing
 * @Date 2021/8/13 14:28
 * @Version 1.0
 **/
public class OperationAdd implements SuperInterface {
    private static OperationAdd add = null;

    private OperationAdd() {
    }

    public static OperationAdd getInstance() {
        if (add == null) {
            synchronized (OperationAdd.class) {
                if (add == null) {
                    add = new OperationAdd();
                }
            }
        }
        return add;
    }

    @Override
    public Object doOperation(List objs) {
        double sum = 0;
        for (Object obj : objs) {
            double temp = Double.valueOf(obj.toString());
            sum += temp;
        }
        return sum;
    }

}
