package com.xzc.timer.schedul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ClassName SpringStaticCronTask
 * @Description 简单demo
 * @Author zhichao.xing
 * @Date 2020/3/16 13:57
 * @Version 1.0
 **/
//@Lazy(false)
//@Component
public class SpringStaticCronTask2 {
    private static final Logger logger = LoggerFactory.getLogger(SpringStaticCronTask2.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void staticCronTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("staticCronTask2 is running...");
    }
}


