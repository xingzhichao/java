package com.xzc.timer.job;

import com.xzc.timer.schedul.QuartzManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName SuperiorGenerateTaskService
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2020/3/16 14:49
 * @Version 1.0
 **/
@Service
public class JobModifyCronService implements Job {
    private Logger logger = LoggerFactory.getLogger(JobModifyCronService.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("JobModifyCronService execute");
        String cron1 = "0/2 * * * * ?";
        String cron2 = "0/30 * * * * ?";
        QuartzManager.modifyJobTime("Job1Service", cron1);
        QuartzManager.modifyJobTime("Job2Service", cron2);
    }
}
