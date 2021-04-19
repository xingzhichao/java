package com.xzc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ThreadLocalTest {
    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    /**
     * Tomcat 的线程池重用了线程
     * 默认最大 200
     * org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat#maxThreads
     *
     * @param userId
     * @return
     */
    @GetMapping("wrong")
    @ResponseBody
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + currentUser.get();
        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping("right")
    @ResponseBody
    public Map right(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        Map result;
        try {
            //设置用户信息之后再查询一次ThreadLocal中的用户信息
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            //汇总输出两次查询结果
            result = new HashMap();
            result.put("before", before);
            result.put("after", after);
        } finally {
            //需要特别注意在代码运行完后，显式地去清空设置的数据
            currentUser.remove();
        }
        return result;
    }
}
