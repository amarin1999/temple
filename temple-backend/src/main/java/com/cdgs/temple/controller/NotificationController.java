package com.cdgs.temple.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/notification")
public class NotificationController {
	
	@GetMapping(path = "/previouspast")
	@PreAuthorize("hasRole('monk') or hasRole('user')")
	public ResponseEntity<ResponseDto<CourseDto>> getAllPreviouspast(){
		ResponseDto<CourseDto> res = new ResponseDto<>();
		try {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}
}