package com.xzc.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ClassName CommandLineRunnerBean
 * @Description 在spring boot应用中
 * 我们可以在程序启动之前执行任何任务。为
 * 了达到这个目的，
 * 我们需要使用CommandLineRunner或ApplicationRunner接口创建bean，
 * spring boot会自动监测到它们
 * <p>
 * <p>
 * CommandLineRunner和ApplicationRunner接口的run()方法
 * 在SpringApplication完成启动时执行。
 * 启动完成之后，应用开始运行。
 * </>
 * </>
 * CommandLineRunner和ApplicationRunner的作用是在程序开始运行前执行任务或记录信息。
 * <p>
 * 默认情况下： ApplicationRunner 先执行   CommandLineRunner 后执行
 * 但是也可以通过 @Order 注解定义执行顺序    或者  implements  Ordered 接口
 * <p>
 * 但是 ：
 * ---->  同时存在 @Order(1)  和   implements Ordered   接口
 * 结论： implements Ordered  生效
 * * <p>
 * @Author zhichao.xing
 * @Date 2021/8/13 14:53
 * @Version 1.0
 **/
@Component
@Order(1)
public class CommandLineRunnerBean implements CommandLineRunner, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerBean.class);

    /**
     * @return void
     * @Description 法接收String数组作为参数
     * @Author xzc
     * @Date 14:56 2021/8/13
     **/
    @Override
    public void run(String... args) {
        String strArgs = Arrays.stream(args).collect(Collectors.joining(","));
        logger.info("启动类的参数args ==> Application started with arguments:" + strArgs);
    }


    @Override
    public int getOrder() {
        return 100;
    }
}
