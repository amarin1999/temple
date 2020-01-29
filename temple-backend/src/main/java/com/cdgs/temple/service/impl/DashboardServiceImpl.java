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

	private DashboardDto mapListEntityToDto(List<DashboardEntity> entities) {
		DashboardDto dto = new DashboardDto();
		try {
			if (entities != null) {
				for (DashboardEntity entity : entities) {
					if (entity.getProvince() != null && entity.getTotalMemberHasCourse() != null) {
						dto.getCountProvince().put(entity.getProvince(), entity.getTotalMemberHasCourse());
					}
				}
			}
		} catch (Exception e) {
			log.error("mapEntityToDto >>> ", e.getMessage());
			e.printStackTrace();
		}
		return dto;
	}

	private DashboardDto mapEntityToDto(DashboardEntity entity) {
		DashboardDto dto = new DashboardDto();
		try {
			if (entity != null) {
				if (entity.getMemberId() != null) {
					dto.setMemberId(entity.getMemberId());
				}

				if (entity.getMemberId() != null) {
					dto.setGenderMale(entity.getGenderM());
				}

				if (entity.getMemberId() != null) {
					dto.setGenderFemale(entity.getGenderF());
				}

				if (entity.getMemberId() != null) {
					dto.setGenderNotspec(entity.getGenderOther());
				}

				if (entity.getTransSelf() != null) {
					dto.setTransport(entity.getTransSelf());
				}
				if (entity.getTransTemple() != null) {
					dto.setTranTemple(entity.getTransTemple());
				}

				if (entity.getNortheast() != null) {
					dto.setNorthEast(entity.getNortheast());
				}

				if (entity.getNorth() != null) {
					dto.setNorth(entity.getNorth());
				}

				if (entity.getEast() != null) {
					dto.setEast(entity.getEast());
				}

				if (entity.getWest() != null) {
					dto.setWestern(entity.getWest());
				}

				if (entity.getSouth() != null) {
					dto.setSouth(entity.getSouth());
				}

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

	@Override
	public DashboardDto getProvinceDashboardDataByRegionId(Long regionId) {
		List<DashboardEntity> dashboardEntities = new ArrayList<>();
		try {
			dashboardEntities = dashboardRepository.getProvinceDataByRegionId(regionId);
		} catch (Exception e) {
			log.error("getAllDataReport >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return mapListEntityToDto(dashboardEntities);
	}

}
