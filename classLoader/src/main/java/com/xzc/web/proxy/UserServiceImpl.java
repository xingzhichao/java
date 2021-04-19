package com.xzc.web.proxy;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/19 17:59
 * @Version 1.0
 **/
public class UserServiceImpl implements IUserService {

    @Override
    public void add(String name) {
        System.out.println("向数据库中插入名为：  " + name + " 的用户");
    }

}
