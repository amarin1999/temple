package com.cdgs.temple.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.DashboardDto;
import com.cdgs.temple.entity.DashboardEntity;
import com.cdgs.temple.repository.DashboardRepository;
import com.cdgs.temple.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private static final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);

	private DashboardRepository dashboardRepository;

	@Autowired
	public DashboardServiceImpl(DashboardRepository dashboardRepository) {
		super();
		this.dashboardRepository = dashboardRepository;
	}

	private List<DashboardDto> mapListEntityToDto(List<DashboardEntity> entities) {
		List<DashboardDto> dtoList = new ArrayList<>();
		try {
			if (entities != null) {
				for (DashboardEntity entity : entities) {
					dtoList.add(mapEntityToDto(entity));
				}
			}
		} catch (Exception e) {
			log.error("mapEntityToDto >>> " + e.getMessage());
			e.printStackTrace();
		}
		return dtoList;
	}

	@Override
	public DashboardDto getReportDashboardMonkData() {
		DashboardEntity dashboardEntity = new DashboardEntity();
		try {
			dashboardEntity = dashboardRepository.getReportDashboardMonkData();
		} catch (Exception e) {
			log.error("getAllDataReport >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return mapEntityToDto(dashboardEntity);
	}

	@Override
	public DashboardDto getReportDashboardUserData(Long memberId) {
		DashboardEntity dashboardEntity = new DashboardEntity();
		try {
			dashboardEntity = dashboardRepository.getReportDashboardUserData(memberId);
		} catch (Exception e) {
			log.error("getAllDataReport >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return mapEntityToDto(dashboardEntity);
	}

	private DashboardDto mapEntityToDto(DashboardEntity entity) {
		DashboardDto dto = new DashboardDto();
		try {
			if (entity != null) {
				if (entity.getMemberId() != null) {
					dto.setMemberId(entity.getMemberId());
				}
				dto.setGenderMale(entity.getGenderM());
				dto.setGenderFemale(entity.getGenderF());
				dto.setGenderNotspec(entity.getGenderOther());

				if (entity.getTransSelf() != null) {
					dto.setTransport(entity.getTransSelf());
				}
				if (entity.getTransTemple() != null) {
					dto.setTranTemple(entity.getTransTemple());
				}

				dto.setNorthEast(entity.getNortheast());
				dto.setNorth(entity.getNorth());
				dto.setEast(entity.getEast());
				dto.setWestern(entity.getWest());
				dto.setSouth(entity.getSouth());

				if (entity.getPassCourse() != null) {
					dto.setPassCourse(entity.getPassCourse());
				}

				if (entity.getStudyCourse() != null) {
					dto.setStudyCourse(entity.getStudyCourse());
				}
			}
		} catch (Exception e) {
			log.error("mapEntityToDto >>> " + e.getMessage());
			e.printStackTrace();
		}
		return dto;

	}

}
