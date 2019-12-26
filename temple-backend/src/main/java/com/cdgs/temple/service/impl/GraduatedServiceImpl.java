package com.cdgs.temple.service.impl;

import com.cdgs.temple.dto.GraduatedDto;
import com.cdgs.temple.entity.GraduatedEntity;
import com.cdgs.temple.repository.GraduatedRepository;
import com.cdgs.temple.repository.MembersHasCourseRepository;
import com.cdgs.temple.service.GraduatedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraduatedServiceImpl implements GraduatedService {
	
	private static final Logger log = LoggerFactory.getLogger(GraduatedServiceImpl.class);

	private final GraduatedRepository graduatedRepository;
	private final MembersHasCourseRepository memberHasCourseRepository;

	@Autowired
	public GraduatedServiceImpl(GraduatedRepository graduatedRepository,
			MembersHasCourseRepository memberHasCourseRepository) {
		this.graduatedRepository = graduatedRepository;
		this.memberHasCourseRepository = memberHasCourseRepository;
	}

	@Override
	public List<GraduatedDto> getAll(Long courseId, Long monkId) {
		List<GraduatedEntity> entities = graduatedRepository.getAll(monkId, courseId);
		List<GraduatedDto> dto = mapEntityesListToDtoList(entities);
		System.out.println(entities);
		System.out.println(courseId + "c");
		System.out.println(monkId + "c");

		return dto;
	}

	@Override
	public boolean update(GraduatedDto body, Long id) {
		System.out.println(body.getStatus() + "  " + id + "  " + body.getMhcId());
		try {
			// MembersHasCourseEntity memberHC = mapDtoToEntity(body);

//			MembersHasCourseEntity memberHasCourse = memberHasCourseRepository
//					.updateStatusMember(body.getStatus(), id, body.getcId());
			System.out.println("before");
			memberHasCourseRepository.updateStatusMember(body.getStatus(), id, body.getMhcId());
			System.out.println("after");
			return true;
		} catch (Exception e) {
			log.error("update >>> " + e.getMessage());
			return false;
		}
	}

	private List<GraduatedDto> mapEntityesListToDtoList(List<GraduatedEntity> entities) {
		List<GraduatedDto> dto = new ArrayList<>();
		for (GraduatedEntity entity : entities) {
			dto.add(mapEntityesToDto(entity));
		}
		return dto;
	}

	private GraduatedDto mapEntityesToDto(GraduatedEntity entity) {
		GraduatedDto dto = new GraduatedDto();
		dto.setuId(entity.getMemberId());
		dto.setMhcId(entity.getMemberHasCourseId());
		dto.setcId(entity.getCourseId());
		dto.setFullname(entity.getDisplayName());
		dto.setStatus(entity.getMhcStatus());
		return dto;
	}

}
