package com.ck.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.ck.util.base.UtilBase;

public class SchedulerUtil extends UtilBase{

	private static SchedulerFactoryBean factory;

	private static Scheduler scheduler;

	private static final String DEFAULT_JOB_GROUP_NAME = "MY_BIGDATA_JOB";

	private static final String DEFAULT_TRIGGER_GROUP_NAME = "MY_BIGDATA_TRIGGER";

	private SchedulerUtil() {}

	static {
		factory = BeanFactory.getBean(SchedulerFactoryBean.class);
		scheduler = factory.getScheduler();
	}

	public static Scheduler getScheduler() {
		return scheduler;
	}

	public static void addCronJob(String jobName, Class<? extends Job> cls, String time,Object scheduleJob) {    
		JobDetail job = JobBuilder.newJob(cls)
				.withIdentity(jobName, DEFAULT_JOB_GROUP_NAME)
				.build();
		// 添加具体任务方法
		job.getJobDataMap().put("scheduleJob", scheduleJob);  
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);  
		// 按新的cronExpression表达式构建一个新的trigger  
		Trigger trigger = TriggerBuilder  
				.newTrigger()  
				.withIdentity(jobName, DEFAULT_TRIGGER_GROUP_NAME)  
				.withSchedule(scheduleBuilder).build();  

		//交给scheduler去调度  
		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error("调度器加载定时任务异常！", e);
		}  

		// 启动    
		try {
			if (!scheduler.isShutdown()) {    
				scheduler.start();    
			}
		} catch (SchedulerException e) {
			logger.error("调度器启动异常！", e);
		}    
	} 

}
