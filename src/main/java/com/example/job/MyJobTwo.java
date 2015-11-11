package com.example.job;

import javax.mail.MessagingException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.example.config.SmtpMailSender;

@Component
public class MyJobTwo extends QuartzJobBean {
	
	@Autowired
	private SmtpMailSender smtpMailSender;
		
	public MyJobTwo() {
		super();
	}
	
	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		System.out.println("Mail being send");
		try {
			smtpMailSender.send("youremail@gmail.com", "Test mail from cronjob", "Howdy");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("Mail sent");
	}

}
