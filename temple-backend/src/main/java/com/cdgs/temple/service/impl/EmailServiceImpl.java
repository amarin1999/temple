package com.cdgs.temple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.cdgs.temple.service.EmailService;

public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(List<String> listEmail, String subject, String text) {
		for (String email : listEmail) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);

			msg.setSubject(subject);
			msg.setText(text);

			javaMailSender.send(msg);
		}
	}
}
