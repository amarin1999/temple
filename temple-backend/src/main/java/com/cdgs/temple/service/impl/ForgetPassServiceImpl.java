package com.cdgs.temple.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.repository.ForgetPassRepository;
import com.cdgs.temple.service.ForgetPassService;
import com.cdgs.temple.service.MemberService;

@Service
public class ForgetPassServiceImpl implements ForgetPassService{
	
	private static final Logger log = LoggerFactory.getLogger(ForgetPassService.class);
	
	private final ForgetPassRepository forgetpassRepository;
	private	final MemberService memberService;

	
	@Autowired
	public ForgetPassServiceImpl(ForgetPassRepository forgetpassRepository, MemberService memberService) {
		this.memberService = memberService;
		this.forgetpassRepository = forgetpassRepository;
		
	}
	
	@Override
	public Integer countUser(String userName, String idCard, String phoneNumber){
		Integer count = forgetpassRepository.countUser(userName, idCard, phoneNumber);
		return count;
	}

}