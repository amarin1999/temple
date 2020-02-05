package com.cdgs.temple.service;

import java.util.List;

public interface EmailService {
	void sendEmail(List<String> listEmail, String subject, String text);
}
