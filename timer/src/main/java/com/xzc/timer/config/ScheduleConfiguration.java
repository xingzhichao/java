package com.xzc.timer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @ClassName ScheduleConfiguration
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/3/16 13:51
 * @Version 1.0
 **/
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
