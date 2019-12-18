package com.cdgs.temple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.repository.ForgetPassRepository;
import com.cdgs.temple.service.ForgetPassService;

@Service
public class ForgetPassServiceImpl implements ForgetPassService{
	
	private ForgetPassRepository forgetpassRepository;

	
	@Autowired
	public ForgetPassServiceImpl(ForgetPassRepository forgetpassRepository) {
		this.forgetpassRepository = forgetpassRepository;
		
	}
	
	@Override
	public Integer countUser(String userName, String idCard, String phoneNumber){
		Integer count = forgetpassRepository.countUser(userName, idCard, phoneNumber);
		return count;
	}

}