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

import com.cdgs.temple.dto.TransportationDto;
import com.cdgs.temple.dto.TransportationTempleDto;
import com.cdgs.temple.service.TransportationService;
import com.cdgs.temple.service.TransportationTempleService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/transportations")
public class TransportationController {
	
	private static final Logger log = LoggerFactory.getLogger(TransportationController.class);
	
	@Autowired
	TransportationService transporatationService;
	
	@Autowired
	TransportationTempleService transportationTempleService;
	
	 /**
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
			return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			log.error(e.getMessage());
			return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}
	}
	
	
	
	/**
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
			return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}
	}
	
	
	/**
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
			return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return  new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
     * deleteTransportation 
     * Description : this controller update status of transportation from 1 to 0.
     * Params : body : TransportationDto, id : Long
     * create : 23/09/2562 By Korawit Ratyiam
     * PS. : This controller can't not delete data because this data is foreign Key of Sensation
     * */
	@PutMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationDto>> deleteTransportation(@PathVariable("id") Long id,@Valid @RequestBody TransportationDto body){
		ResponseDto<TransportationDto> res  = new ResponseDto<TransportationDto>();
		Boolean transportation;
		try {
			transportation = transporatationService.deleteTransportation(id, body);
			if(transportation) {
				res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
				res.setCode(200);
			} else {
				res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
				throw new Exception ("transportation is using");
			}
			return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<ResponseDto<TransportationDto>>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/********************************************************************************************************************************
	 * -------------------------------------Manage Data Transportation of Temple ---------------------------------------------------
	 * */
	
	/**
	 * getTransportationTemple()
	 * This function for get transportation of temple 
	 * 
	 	*/
	@GetMapping(path = "/temple")
	public ResponseEntity<ResponseDto<TransportationTempleDto>> getTransportationTemple(){
		List<TransportationTempleDto> templeDto = new ArrayList<>();
		ResponseDto<TransportationTempleDto> res = new ResponseDto<TransportationTempleDto>();
		try {
			templeDto = transportationTempleService.getTransportationTempleName();
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(templeDto);
			res.setCode(200);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * insertTransportationTemple()
	 * This function for insert transportation of temple
	 * 
	 	*/
	
	@PostMapping(path = "/temple")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationTempleDto>> insertTransportationTemple (@Valid @RequestBody TransportationTempleDto body){
		List<TransportationTempleDto> templeDto = new ArrayList<>();
		TransportationTempleDto transportationTemple = new TransportationTempleDto();
		ResponseDto<TransportationTempleDto> res = new ResponseDto<TransportationTempleDto>();
		try {
			body.setStatus(true);
			transportationTemple = transportationTempleService.createTransportationTemple(body);
			if(transportationTemple != null) {
				templeDto.add(transportationTemple);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(templeDto);
			res.setCode(200);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.OK);
		}catch (Exception e){
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(406);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/**
	 * updateTransportationTemple()
	 * This function for update transportation of temple
	 * params: id,  body: TransportationTempleDto
	 * 
	 	*/
	
	@PutMapping(path = "/temple/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationTempleDto>> updateTransportationTemple(@PathVariable("id") Long id,@Valid @RequestBody TransportationTempleDto body){
		List<TransportationTempleDto> templeDto = new ArrayList<>();
		ResponseDto<TransportationTempleDto> res = new ResponseDto<TransportationTempleDto>();
		TransportationTempleDto transportationTemple = new TransportationTempleDto();
		try {
			transportationTemple = transportationTempleService.updateTransportationTemple(id, body);
			if(transportationTemple != null) {
				templeDto.add(transportationTemple);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(templeDto);
			res.setCode(200);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res,HttpStatus.OK);
		}catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * deleteTransportationTemple()
	 * This function for delete transportation of temple
	 * params: id,  body: TransportationTempleDto
	 * 
	 	*/
	
	@PutMapping(path= "/temple/delete/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<TransportationTempleDto>> deleteTransportationTemple(@PathVariable("id") Long id,@Valid @RequestBody TransportationTempleDto body){
		ResponseDto<TransportationTempleDto> res = new ResponseDto<TransportationTempleDto>();
		Boolean delTransportationTemple;
		try {
			delTransportationTemple = transportationTempleService.deleteTransportationTemple(id, body);
			if(delTransportationTemple) {
				res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
				res.setCode(200);
			} else {
				res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
				throw new Exception ("transportationTemple is using");
			}
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.OK);
		}catch (Exception e){
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<ResponseDto<TransportationTempleDto>>(res, HttpStatus.BAD_REQUEST);
		}		
	}
	
	
	

	
	
	
}
