package com.example.config;
  
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.example.job.MyJobTwo;
  
@Configuration 
@ComponentScan("com.example") 
public class QuartzConfiguration {
	
	// we need to create a bean that will excuted by MethodInvokingJobDetailFactoryBean
	// in this case we have myJobOne is the simple bean
	/*@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("myJobOne");
		obj.setTargetMethod("myTask");
		return obj;
	}*/
	
	// This trigger will schedule the job after 3 seconds and repeat after every 30 seconds for 3+1 times.
	/*@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setRepeatInterval(30000);
		stFactory.setRepeatCount(1);
		return stFactory;
	}*/
	
	// We use it to configure complex job such as job scheduling using cron-expression
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(MyJobTwo.class);
		return factory;
	}
	
	// CronTriggerFactoryBean configures JobDetailFactoryBean
	// We also configure start delay, trigger name, and cron-expression to schedule the job
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
		return stFactory;
	}
	
	@Bean
	public JobFactory jobFactory() {
	    return new AutowiringSpringBeanJobFactory();
	}
	
	// SchedulerFactoryBean use to register the triggers 
	// those registered triggers will be executed
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		//scheduler.setTriggers(simpleTriggerFactoryBean().getObject());
		scheduler.setJobFactory(jobFactory());
		return scheduler;
	}
}  
 