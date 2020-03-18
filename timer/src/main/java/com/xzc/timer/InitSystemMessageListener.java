package com.xzc.timer;


import com.xzc.timer.job.Job1Service;
import com.xzc.timer.job.Job2Service;
import com.xzc.timer.job.JobModifyCronService;
import com.xzc.timer.schedul.QuartzManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 系统初始化程序
 *
 * @author Gavin
 * @version 1.0 Gavin 2017年6月6日 上午11:58:09
 * @title Init.java
 * @since 2017年6月6日 上午11:58:09
 */
@WebListener
public class InitSystemMessageListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletcontextevent) {
        // ***系统初始化
        System.out.println("系统初始化");
        System.out.println("执行定时任务");
        String cron1 = "0/5 * * * * ?";
        String cron2 = "0/10 * * * * ?";
        String cronModify = "0/30 * * * * ?";
        QuartzManager.addJob("Job1Service", Job1Service.class, cron1);
        QuartzManager.addJob("Job2Service", Job2Service.class, cron2);
        QuartzManager.addJob("JobModifyCronService", JobModifyCronService.class, cronModify);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletcontextevent) {
    }
}
