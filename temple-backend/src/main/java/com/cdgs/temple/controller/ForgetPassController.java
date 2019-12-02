package com.cdgs.temple.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.ForgetPassDto;
import com.cdgs.temple.dto.ResponseCountDto;
import com.cdgs.temple.service.ForgetPassService;
import com.cdgs.temple.util.ResponseDto;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/forgetpass")
public class ForgetPassController {
	
	private ForgetPassService forgetPassService;

	@Autowired
	public ForgetPassController(ForgetPassService forgetPassService) {
		this.forgetPassService = forgetPassService;
	}
	
    @PostMapping(path = "/")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgetPassDto body){
    	ResponseDto<ResponseCountDto> res = new ResponseDto<>();
//    	System.out.println(body.getEmail()+" | "+body.getUsername());
    	Integer count = forgetPassService.countUser(body.getEmail(), body.getUsername());
//    	System.out.println(count);
    	try {
//    		System.out.println("Out If "+body.getEmail()+" | "+body.getUsername());
    		if (count > 0) {
//    			System.out.println("In If "+body.getEmail()+" | "+body.getUsername());
    			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
    			res.setCode(200);
    			return new ResponseEntity<>(res, HttpStatus.OK);
    		}else {
    			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
    			res.setCode(204);
    			return new ResponseEntity<>(res, HttpStatus.OK);
    		}
    	} catch (Exception e) {
    		System.out.println("Error");
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(401);
    		return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    	}
    }

}
