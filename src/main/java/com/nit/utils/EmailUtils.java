package com.nit.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtils {
	@Autowired private JavaMailSender sender;

	public String sendEmail(File file) {
		MimeMessage mimeMessage
		= sender.createMimeMessage();

	try {

		// Setting multipart as true for attachments to
		// be send

		MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
	//	mimeMessageHelper.setFrom("springboot574@gmail.com");
		mimeMessageHelper.setTo("premkumar.kalla@gmail.com");
		mimeMessageHelper.setSubject("your report");
		mimeMessageHelper.setText("<h2> please download your report</h2>",true);
		System.out.println(file);
		mimeMessageHelper.addAttachment(file.getName(), file);
		// Sending the mail
		sender.send(mimeMessage);
		return "Mail sent Successfully";
	}

	// Catch block to handle MessagingException
	catch (MessagingException e) {

		// Display message when exception occurred
		return "Error while sending mail!!!";
	}
}

	
}
