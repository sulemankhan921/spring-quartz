package com.example.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SmtpMailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	// sending email
	public void send(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true); // true indicates
													   // multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding attachments, etc.  
		javaMailSender.send(message);
	}
	
	// sending email using bcc()
	public void sendBcc(String bcc, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true); // true indicates
													   // multipart message
		String[] recepients = bcc.split(",");
		helper.setSubject(subject);
		helper.setBcc(recepients);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding attachments, etc.  
		javaMailSender.send(message);
	}	
}