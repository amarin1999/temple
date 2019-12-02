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
	public Integer countUser(String email, String userName){
//		System.out.println("In Service Imp :"+email+" | "+userName);
		Integer count = forgetpassRepository.countUser(email, userName);
		return count;
	}

}