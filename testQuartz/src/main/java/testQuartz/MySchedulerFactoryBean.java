package testQuartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class MySchedulerFactoryBean extends SchedulerFactoryBean {
	
	private String schedulerName;
	private ApplicationContext applicationContext;

	public String getSchedulerName() {
		return schedulerName;
	}
	
	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("++++++++a---3");
		// 进行job和触发器进行结合的地方
		// (1) 创建计划器容器
		SchedulerFactory schedulerFactory = BeanUtils.instantiateClass(StdSchedulerFactory.class);
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		// (2) 创建Job
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(MyJob.class.getSimpleName(), MyJob.class.getSimpleName()).build();
		
		// (3) 创建触发器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(MyJob.class.getSimpleName(), MyJob.class.getSimpleName()).withSchedule(scheduleBuilder).build();
		
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		System.out.println("++++++++b---2");
	}

	public void setBeanName(String name) {
		if (this.schedulerName == null) {
			this.schedulerName = name;
		}
		System.out.println("++++++++c---1");
	}

	public Scheduler getObject() {
		System.out.println("++++++++d");
		return null;
	}

	public Class<? extends Scheduler> getObjectType() {
		System.out.println("++++++++e");
		return null;
	}

	public boolean isSingleton() {
		System.out.println("++++++++f---4");
		return true;
	}

}
