/*
 * The default job factory implementation AdaptableJobFactory doesn't
 * have autowiring capability.
 * If we want to do dependency injection in one or our trigger bean class 
 * we need to use this class in order to enable autowiring capability
 * 
 * */

package com.example.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory
		implements ApplicationContextAware {
	private transient AutowireCapableBeanFactory beanFactory;

	public void setApplicationContext(final ApplicationContext context) {
		beanFactory = context.getAutowireCapableBeanFactory();
	}

	@Override
	public Object createJobInstance(final TriggerFiredBundle bundle)
			throws Exception {
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job); // the magic is done here
		return job;
	}
}
