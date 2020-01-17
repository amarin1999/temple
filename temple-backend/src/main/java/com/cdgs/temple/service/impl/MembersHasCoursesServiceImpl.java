package com.cdgs.temple.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.MembersHasCourseDto;
import com.cdgs.temple.entity.MembersHasCourseEntity;
import com.cdgs.temple.repository.MembersHasCourseRepository;
import com.cdgs.temple.service.MembersHasCourseService;

@Service
public class MembersHasCoursesServiceImpl implements MembersHasCourseService {

	private final MembersHasCourseRepository memberHasCourseRepository;

	@Autowired
	public MembersHasCoursesServiceImpl(MembersHasCourseRepository memberHasCourseRepository) {
		this.memberHasCourseRepository = memberHasCourseRepository;
	}

	@Override
	public List<MembersHasCourseDto> getMembersHasCourse() {
		return mapListEntityToDto(memberHasCourseRepository.findAll());
	}

	@Override
	public MembersHasCourseDto createEntityMembersHasCourse() {
		return null;
	}

	@Override
	public MembersHasCourseDto updateMembersHasCourse() {
		return null;
	}

	@Override
	public MembersHasCourseDto deleteMembersHasCourse() {
		return null;
	}

	@Override
	public Long countForPassCourses(Long memberId) {
		return memberHasCourseRepository.CountForPassCourse(memberId);
	}

	@Override
	public List<MembersHasCourseDto> getMembersByCourse(Long courseId) {
		return mapListEntityToDto(memberHasCourseRepository.findAllByCourseId(courseId));
	}

	private List<MembersHasCourseDto> mapListEntityToDto(List<MembersHasCourseEntity> entities) {
		List<MembersHasCourseDto> listDto = new ArrayList<>();
		if (!entities.isEmpty()) {
			for (MembersHasCourseEntity entity : entities) {
				listDto.add(mapEntityToDto(entity));
			}
		}
		return listDto;

	}

	private MembersHasCourseDto mapEntityToDto(MembersHasCourseEntity entity) {
		MembersHasCourseDto dto = new MembersHasCourseDto();
		if (entity != null) {
			dto.setMembersHasCourseId(entity.getMembersHasCourseId());
			dto.setMemberId(entity.getMemberId());
			dto.setCourseId(entity.getCourseId());
			dto.setRegisterDate(entity.getRegisterDate());
			dto.setStatus(entity.getMhcStatus());
			dto.setSenseId(entity.getSenseId());
		}
		return dto;
	}
	
}
