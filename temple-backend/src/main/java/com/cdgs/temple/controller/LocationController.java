package com.cdgs.temple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.cdgs.temple.dto.LocationDto;
import com.cdgs.temple.service.LocationService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/locations")
public class LocationController {
	
	@Autowired
	LocationService locationService;
	@GetMapping(path = "")
	@PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')" )
		public ResponseEntity<ResponseDto<LocationDto>> getLocations() {
			List<LocationDto> locations = new ArrayList<LocationDto>();
			ResponseDto<LocationDto> res = new ResponseDto<LocationDto>();
			try {
				locations = locationService.getLocations();
				res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
				res.setData(locations);
				res.setCode(200);
			} catch (Exception e) {
				res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
				res.setErrorMessage(e.getMessage());
				res.setCode(200);
			}
			return new ResponseEntity<ResponseDto<LocationDto>>(res, HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
	public ResponseEntity<ResponseDto<LocationDto>> getLocation(@PathVariable("id") Long id) {
		ResponseDto<LocationDto> res = new ResponseDto<LocationDto>();
		List<LocationDto> locations = new ArrayList<LocationDto>();
		LocationDto location = new LocationDto();
		try {
			location = locationService.getLocation(id);
			if (location != null) {
				locations.add(location);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(locations);
			res.setCode(200);

		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(200);
		}
		return new ResponseEntity<ResponseDto<LocationDto>>(res, HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<LocationDto>> putCustomer(@PathVariable("id") Long id,
			@Valid @RequestBody LocationDto body) {
		ResponseDto<LocationDto> res = new ResponseDto<LocationDto>();
		List<LocationDto> locations = new ArrayList<LocationDto>();
		LocationDto location = new LocationDto();
		try {
			location = locationService.updateLocation(id, body);
			if (location != null) {
				locations.add(location);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(locations);
			res.setCode(200);
		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(200);
		}
		return new ResponseEntity<ResponseDto<LocationDto>>(res, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<LocationDto>> postLocations(@Valid @RequestBody LocationDto body) {
		ResponseDto<LocationDto> res = new ResponseDto<LocationDto>();
		List<LocationDto> locations = new ArrayList<LocationDto>();
		LocationDto location = new LocationDto();
		try {
			location = locationService.createLocation(body);
			if (location != null) {
				locations.add(location);
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(locations);
			res.setCode(200);
		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(200);
		}
		return new ResponseEntity<ResponseDto<LocationDto>>(res, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<LocationDto>> deleteLocation(@PathVariable("id") long id) {
		ResponseDto<LocationDto> res = new ResponseDto<LocationDto>();
		if (locationService.deleteLocation(id)) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setCode(204);
		} else {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setCode(200);
		}
		return new ResponseEntity<ResponseDto<LocationDto>>(res, HttpStatus.OK);
	}
	// end location


}
