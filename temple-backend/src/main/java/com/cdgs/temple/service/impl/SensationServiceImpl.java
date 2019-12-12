package com.cdgs.temple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.SensationDto;
import com.cdgs.temple.entity.SensationEntity;
import com.cdgs.temple.repository.SensationRepository;
import com.cdgs.temple.service.SensationService;

@Service
public class SensationServiceImpl implements SensationService{
	
private final SensationRepository sensationRepository;
	
	@Autowired
	public SensationServiceImpl(SensationRepository sensationRepository) {
		this.sensationRepository = sensationRepository;
	}
	
	public SensationDto createSensation(SensationDto body) {
		SensationEntity entity = new SensationEntity();
		try {
			if(body != null) {
				entity = sensationRepository.save(mapDtoToEntity(body));	
			}
			return mapEntityToDto(entity);
				
		} catch (Exception e) {
			return null;
		}
	}

	private SensationDto mapEntityToDto(SensationEntity entity) {
		SensationDto dto = new SensationDto();
		try {
			if(entity != null) {
				dto.setId(entity.getSenseId());
				dto.setExpected(entity.getSenseExpected());
				dto.setExperience(entity.getSenseExprience());
//				dto.setTransportationId(entity.getTransportationId());
			}
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	private SensationEntity mapDtoToEntity(SensationDto sense) {
		SensationEntity entity = new SensationEntity();
		System.out.println(sense.toString());
		try {
			if(sense != null) {
				entity.setSenseId(sense.getId());
				entity.setSenseExpected(sense.getExpected());
				entity.setSenseExprience(sense.getExperience());
//				entity.setTransportationId(sense.getTransportationId());
			}
			return entity;
		}catch (Exception e) {
			return null;
		}
		
	}


}
