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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.MapDto;
import com.cdgs.temple.dto.TitleNameDto;
import com.cdgs.temple.entity.TitleNamesEntity;
import com.cdgs.temple.service.MapService;
import com.cdgs.temple.service.TitleNameService;
import com.cdgs.temple.service.impl.TitleNameServiceImpl;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1")

public class MapController {
		

	@Autowired
	MapService mapService;

	@GetMapping(path = "/test")
	public ResponseEntity<ResponseDto<MapDto>> getTest() {
		List<MapDto> dto = new ArrayList<MapDto>();
		ResponseDto<MapDto> res = new ResponseDto<MapDto>();
		try {
			dto = mapService.getMap();
			res.setResult("test status");
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<ResponseDto<MapDto>>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<ResponseDto<MapDto>>(res, HttpStatus.OK);
		}
	}
}
