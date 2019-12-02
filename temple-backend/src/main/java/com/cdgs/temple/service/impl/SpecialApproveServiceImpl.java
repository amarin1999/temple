package com.cdgs.temple.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cdgs.temple.entity.MembersHasCourseEntity;
import com.cdgs.temple.entity.TempSpecialApproveEntity;
import com.cdgs.temple.repository.MembersHasCourseRepository;
import com.cdgs.temple.repository.TempSpecialApproveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.SensationDto;
import com.cdgs.temple.dto.SpecialApproveDto;
import com.cdgs.temple.entity.SpecialApproveEntity;
import com.cdgs.temple.repository.SpecialApproveRepository;
import com.cdgs.temple.service.SensationService;
import com.cdgs.temple.service.SpecialApproveService;

@Service
public class SpecialApproveServiceImpl implements SpecialApproveService{

	private SpecialApproveRepository specialApproveRepository;
	private TempSpecialApproveRepository tempSpecialApproveRepository;
	private MembersHasCourseRepository membersHasCourseRepository;
	private SensationService sensationService;
	@Autowired
	public SpecialApproveServiceImpl (
			SpecialApproveRepository specialApproveRepository,
			TempSpecialApproveRepository tempSpecialApproveRepository,
			MembersHasCourseRepository membersHasCourseRepository,
			SensationService sensationService
	) {
		this.specialApproveRepository = specialApproveRepository;
		this.tempSpecialApproveRepository = tempSpecialApproveRepository;
		this.membersHasCourseRepository = membersHasCourseRepository;
		this.sensationService = sensationService;
	}

	@Override
	public List<SpecialApproveDto> getAll(Long memberId, Long courseId) {
		return mapTempEntityListToDto(tempSpecialApproveRepository.getAll(memberId, courseId));
	}

	@Override
	public SpecialApproveDto getById(Long memberId, Long id) {
		return mapEntityToDto(specialApproveRepository.findBySpecialApproveId(id));
	}

	@Override
	public SpecialApproveDto create(SpecialApproveDto body) {
		SensationDto tempSensation = new SensationDto();
		SensationDto sensation = new SensationDto();
		
		tempSensation.setId(body.getSenseId());
    	tempSensation.setExpected(body.getExpected());
    	tempSensation.setExperience(body.getExperience());
    	tempSensation.setTransportationId(body.getTransportationId());
    	sensation = sensationService.createSensation(tempSensation);
    	body.setSenseId(sensation.getId());
    	
		SpecialApproveEntity entity = mapDtoToEntity(body);

		try {
			return mapEntityToDto(specialApproveRepository.save(entity));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public SpecialApproveDto delete(Long courseId, Long memberId) {
		SpecialApproveEntity data = specialApproveRepository.findByCourseIdAndMemberIdAndStatus(courseId, memberId, "2");
		if(data != null) {
			data.setSpaStatus("3");
			data.setLastUpdate(LocalDateTime.now());
			return mapEntityToDto(specialApproveRepository.save(data));
		}
		return null;
	}

	@Override
	public SpecialApproveDto update(SpecialApproveDto body, Long memberId) {
		SpecialApproveEntity data = specialApproveRepository.fetchBySaIdAndMemberId(body.getSpecialApproveId(), memberId);
		if(data != null && !body.getStatus().equals("2") && !body.getStatus().equals("3")) {
			data.setSpaStatus(body.getStatus());
			data.setLastUpdate(LocalDateTime.now());
			return mapEntityToDto(specialApproveRepository.save(data));
		}
		return null;
	}

	@Override
	public boolean approve(SpecialApproveDto data) {
		MembersHasCourseEntity mhc = new MembersHasCourseEntity();
		mhc.setMemberId(data.getMemberId());
		mhc.setCourseId(data.getCourseId());
		mhc.setMhcStatus('2');
		mhc.setSenseId(data.getSenseId());
		if (membersHasCourseRepository.save(mhc) != null){
			return true;
		}
		return false;
	}
	
	@Override
	public SpecialApproveDto getApproveByCourseIdAndMemberId(Long courseId, Long memberId) {
		return mapEntityToDto(specialApproveRepository.findByCourseIdAndMemberId(courseId,memberId));
	}
	
	@Override
	public Boolean cancelApproveOutTime(Long specialApproveId) {
		try {
			specialApproveRepository.cancelApproveOutTime(specialApproveId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<SpecialApproveDto> getMemberOutTime(Long memberId, Long courseId) {
		return mapTempEntityListToDto(tempSpecialApproveRepository.getMemberOutTime(memberId, courseId));
	}
	
	@Override
	public SpecialApproveDto getByCourseIdAndMemberId(Long courseId,Long memberId) {
		return mapEntityToDto(specialApproveRepository.getByCourseIdAndMemberId(courseId, memberId));
	}

	private List<SpecialApproveDto> mapEntityListToDto(List<SpecialApproveEntity> entities){
		List<SpecialApproveDto> dto = new ArrayList<>();
		for (SpecialApproveEntity entity : entities) {
			dto.add(mapEntityToDto(entity));
		}
		return dto;
	}

	private List<SpecialApproveDto> mapTempEntityListToDto(List<TempSpecialApproveEntity> entities) {
		List<SpecialApproveDto> dto = new ArrayList<>();
		for (TempSpecialApproveEntity entity : entities) {
			dto.add(mapTempEntityToDto(entity));
		}
		return dto;
	}

	private SpecialApproveDto mapEntityToDto(SpecialApproveEntity entity) {
		SpecialApproveDto dto = new SpecialApproveDto();
		dto.setSpecialApproveId(entity.getSpecialApproveId());
		dto.setCourseId(entity.getCourseId());
		dto.setCreateDate(entity.getCreateDate());
		dto.setDetail(entity.getSpaDetail());
		dto.setLastUpdate(entity.getLastUpdate());
		dto.setMemberId(entity.getMemberId());
		dto.setStatus(entity.getSpaStatus());
		dto.setSenseId(entity.getSenseId());
		
		
		return dto;
	}

	private SpecialApproveDto mapTempEntityToDto(TempSpecialApproveEntity entity) {
		SpecialApproveDto dto = new SpecialApproveDto();
		dto.setSpecialApproveId(entity.getSpecialApproveId());
		dto.setMemberId(entity.getMemberId());
		dto.setDisplayName(entity.getDisplayName());
		dto.setDetail(entity.getSpaDetail());
		dto.setTransportationName(entity.getTransportation());
		return dto;
	}

	private SpecialApproveEntity mapDtoToEntity(SpecialApproveDto dto) {
		SpecialApproveEntity entity = new SpecialApproveEntity();
		entity.setCourseId(dto.getCourseId());
		entity.setCreateDate(dto.getCreateDate());
		entity.setSpaDetail(dto.getDetail());
		entity.setLastUpdate(dto.getLastUpdate());
		entity.setMemberId(dto.getMemberId());
		entity.setSpaStatus(dto.getStatus());
		entity.setSenseId(dto.getSenseId());
		return entity;
	}


}