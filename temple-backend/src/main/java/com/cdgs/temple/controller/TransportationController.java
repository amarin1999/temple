package com.cdgs.temple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.SensationDto;
import com.cdgs.temple.dto.TitleNameDto;
import com.cdgs.temple.dto.TransportationDto;
import com.cdgs.temple.service.TransportationService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/transportations")
public class TransportationController {
	
	private static final Logger log = LoggerFactory.getLogger(TransportationController.class);
	
	@Autowired
	TransportationService transporatationService;
	
	 /*
     * getTransportation 
     * Description : this controller get data of transportation.
     * Params : -
     * create : 23/09/2562 By Korawit Ratyiam
     * */
	
	@GetMapping(path = "")
	public ResponseEntity<ResponseDto<TransportationDto>> getTransportation(){
		List<TransportationDto> dto = new ArrayList<>();
		ResponseDto<TransportationDto> res = new ResponseDto<TransportationDto>();
		try {
			dto = transporatationService.getTransportationName();
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(201);
		}
		return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
	}
	
	/*
     * postTransporatation 
     * Description : this controller insert data of transportation.
     * Params : body : TransportationDto
     * create : 23/09/2562 By Korawit Ratyiam
     * */
	@PostMapping(path = "")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationDto>> postTransporatation(@Valid @RequestBody TransportationDto body){
		List<TransportationDto> dto = new ArrayList<>();
		TransportationDto transportation = new TransportationDto();
		ResponseDto<TransportationDto> res = new ResponseDto<TransportationDto>();
		System.out.println(body.getName());
		
		try {
			body.setStatus(true);
			transportation = transporatationService.createTransportation(body);
			if(transportation != null) {
				dto.add(transportation);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(201);
		}
		return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
	}

	/*
     * updateTransportation 
     * Description : this controller update data of transportation.
     * Params : body : TransportationDto, id : Long
     * create : 23/09/2562 By Korawit Ratyiam
     * */
	
	@PutMapping(path = "/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationDto>> updateTransportation(@PathVariable("id") Long id,@Valid @RequestBody TransportationDto body){
		List<TransportationDto> dto = new ArrayList<>();
		ResponseDto<TransportationDto> res  = new ResponseDto<TransportationDto>();
		TransportationDto transportation = new TransportationDto();
		try {
			transportation = transporatationService.updateTransportation(id, body);
			if(transportation != null) {
				dto.add(transportation);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(201);
		}
		return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
	}
	
	/*
     * deleteTransportation 
     * Description : this controller update status of transportation from 1 to 0.
     * Params : body : TransportationDto, id : Long
     * create : 23/09/2562 By Korawit Ratyiam
     * PS. : This controller can't not delete data because this data is foreign Key of Sensation
     * */
	@PutMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationDto>> deleteTransportation(@PathVariable("id") Long id,@Valid @RequestBody TransportationDto body){
		List<TransportationDto> dto = new ArrayList<>();
		ResponseDto<TransportationDto> res  = new ResponseDto<TransportationDto>();
		TransportationDto transportation = new TransportationDto();
		try {
			transportation = transporatationService.deleteTransportation(id, body);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setCode(200);
			
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(201);
		}
		
		return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
	}
	
}
