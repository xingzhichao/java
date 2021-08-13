package com.xzc.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ClassName ApplicationRunnerBean
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/8/13 15:04
 * @Version 1.0
 **/
@Component
@Order(10)
public class DemoApplicationRunnerBean implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationRunnerBean.class);

    /**
     * @return void
     * @Description 参数为  ApplicationArguments
     * @Author xzc
     * @Date 15:05 2021/8/13
     **/
    @Override
    public void run(ApplicationArguments arg0) {
        String strArgs = Arrays.stream(arg0.getSourceArgs()).collect(Collectors.joining("|"));
        logger.info("Application started with arguments:" + strArgs);
    }
}