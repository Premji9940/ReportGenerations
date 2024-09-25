// Java Program to Illustrate Creation Of
// Service Interface

package com.nit.service;

import org.springframework.mail.javamail.JavaMailSender;

import com.nit.entity.EmailDetails;

// Interface
public interface EmailService {

	// Method
	// To send a simple email
	String sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details,JavaMailSender javaMailSender);
	
}
