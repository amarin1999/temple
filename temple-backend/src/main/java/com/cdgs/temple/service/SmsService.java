package com.cdgs.temple.service;

public interface SmsService {
	void sendSms(String fromPhoneNo, String toPhoneNo, String messageText);
}
