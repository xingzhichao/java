package com.xzc.desgin.strategy;

import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test
 * @Description 入口
 * @Author zhichao.xing
 * @Date 2021/8/13 14:37
 * @Version 1.0
 **/
public class Test {
    /**
     * @return void
     * @Description 设计模式之策略模式（排除过多的if-else，且无需反射和注解的实现方式）
     * 1.设计模式选型---行为型（策略模式），为消除if-else 实现代码的松耦合而存在
     * <p>
     * 2.思路：将行为的决策权交给枚举，有多少种情况就需要定义多少个枚举类型，匹配时根据枚举类型中的键值进行决策。
     * <p>
     * 3.talk is cheap, show me the code
     * @Author xzc
     * @Date 14:45 2021/8/13
     **/
    public static void main(String[] args) {
        Map request = new HashMap<String, String>(2);
        request.put("type", "SUB");
        request.put("data", "[100,20,30]");
        doOperation(request);
    }

    /**
     * @return void
     * @Description 具体类实现时用到了单例模式，
     * 避免多次实例化造成内存浪费，
     * 使用枚举有些类似switch形式，
     * <p>
     * 但是具体匹配需要交给前端传值来调用，情况过多时前后台交互的情况过多，不利于前端代码的更改
     * @Author xzc
     * @Date 14:47 2021/8/13
     **/
    public static void doOperation(Map<String, String> request) {
        try {
            String type = request.get("type");
            String data = request.get("data");
            JSONArray array = JSONArray.fromObject(data);
            OperType ot = OperType.valueOf(type);
            SuperInterface add = ot.getOption();
            System.out.println(add);
            Object res = add.doOperation(array);
            System.out.println("add res:" + res);
        } catch (Exception e) {
            System.out.println("执行出错");
            e.printStackTrace();
        }
    }
}
