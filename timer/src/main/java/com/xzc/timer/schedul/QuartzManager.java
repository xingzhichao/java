package com.xzc.timer.schedul;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

/**
 * 定时任务管理类
 * 
 * @author Gavin
 * @version 1.0
 */
public class QuartzManager {
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "ESSENCE_JOBGROUP";
	private static String TRIGGER_GROUP_NAME = "ESSENCE_TRIGGERGROUP";

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, Class<? extends Job> jobClass , String time) {

		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail =  JobBuilder.newJob(jobClass)
					.withIdentity(jobName, JOB_GROUP_NAME).build();  
			// 触发器
			CronTriggerImpl trigger = new CronTriggerImpl();// 触发器名,触发器组
			trigger.setGroup(TRIGGER_GROUP_NAME);
			trigger.setName(jobName);
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
				System.out.println(jobName + " Task Load Success.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 */
	public static void modifyJobTime(String jobName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
			
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME));
				Class<? extends Job> objJobClass = jobDetail.getJobClass();
				removeJob(jobName);
				addJob(jobName, objJobClass, time);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 */
	public static void removeJob(String jobName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			TriggerKey jobkey=new TriggerKey(jobName, TRIGGER_GROUP_NAME);
			sched.pauseTrigger(jobkey);// 停止触发器
			sched.unscheduleJob(jobkey);// 移除触发器
			sched.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));// 删除任务
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 启动所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}