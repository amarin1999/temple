package com.cdgs.temple.service.impl;


import java.util.Arrays;
import java.util.List;

import com.cdgs.temple.service.SmsService;

import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Api.SmsApi;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;



public class SmsServiceImpl implements SmsService {
	static final String USERNAME = "TempleAPIv123456@gmail.com";
	static final String API_KEY = "EFEC314F-7686-5F2D-A87B-E389F8486022";
	
	@Override
	public void sendSms(String fromPhoneNo, String toPhoneNo, String messageText) {
		ApiClient defaultClient = new ApiClient();
	    defaultClient.setUsername(USERNAME);
	    defaultClient.setPassword(API_KEY);
	    SmsApi apiInstance = new SmsApi(defaultClient);

	    SmsMessage smsMessage=new SmsMessage();
	    smsMessage.setBody(messageText);
	    smsMessage.setTo(toPhoneNo);
	    smsMessage.setSource("sdk");

	    List<SmsMessage> smsMessageList=Arrays.asList(smsMessage);
	    // SmsMessageCollection | SmsMessageCollection model
	    SmsMessageCollection smsMessages = new SmsMessageCollection();
	    smsMessages.messages(smsMessageList);
	    try {
	        String result = apiInstance.smsSendPost(smsMessages);
	        System.out.println(result);
	    } catch (ApiException e) {
	        System.err.println("Exception when calling SmsApi#smsSendPost");
	        e.printStackTrace();
	    }
	}
}
