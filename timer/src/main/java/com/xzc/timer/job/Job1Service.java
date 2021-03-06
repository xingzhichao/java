package com.xzc.timer.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName SuperiorGenerateTaskService
 * @Description job1
 * @Author zhichao.xing
 * @Date 2020/3/16 14:49
 * @Version 1.0
 **/
@Service
public class Job1Service implements Job {

    private Logger logger=LoggerFactory.getLogger(Job1Service.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("job1 execute");
    }
}
