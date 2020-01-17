package com.cdgs.temple.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.ReportGenDto;
import com.cdgs.temple.entity.ReportGenEntity;
import com.cdgs.temple.repository.ReportGenRepository;
import com.cdgs.temple.service.ReportGenService;

@Service
public class ReportGenServiceImpl implements ReportGenService {
	
	private static final Logger log = LoggerFactory.getLogger(ReportGenServiceImpl.class);
	
	private ReportGenRepository reportGenRepository;
	
	@Autowired
	public ReportGenServiceImpl(ReportGenRepository reportGenRepository) {
		super();
		this.reportGenRepository = reportGenRepository;
	}

	@Override
	public List<ReportGenDto> getAllDataReport() {
		List<ReportGenEntity> reportGenEntities = new ArrayList<>();
		try {
			reportGenEntities = reportGenRepository.getAllDataReport();
		} catch (Exception e) {
			log.error("getAllDataReport >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return mapListEntityToDto(reportGenEntities);
	}
	
	@Override
	public List<ReportGenDto> getDataReportByCourseId(Long courseId) {
		List<ReportGenEntity> reportGenEntities = new ArrayList<>();
		try {
			reportGenEntities = reportGenRepository.getDataReportByCourseId(courseId);
		} catch (Exception e) {
			log.error("getAllDataReport >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return mapListEntityToDto(reportGenEntities);
	}
	
	private List<ReportGenDto> mapListEntityToDto (List<ReportGenEntity> entities) {
		List<ReportGenDto> dtoList = new ArrayList<>();
		try {
			if (entities != null) {
				for (ReportGenEntity entity : entities) {
					dtoList.add(mapEntityToDto(entity));
				}
			}
		} catch (Exception e) {
			log.error("mapEntityToDto >>> " + e.getMessage());
			e.printStackTrace();
		}
		return dtoList;
	}
	
	private ReportGenDto mapEntityToDto (ReportGenEntity entity) {
		ReportGenDto dto = new ReportGenDto();
		try {
			if(entity != null) {
				dto.setCoursesId(entity.getCourseId());
				dto.setCoursesName(entity.getCourseName());
				dto.setGenderMale(entity.getGenderM());
				dto.setGenderFemale(entity.getGenderF());
				dto.setGenderNotspec(entity.getGenderOther());
				dto.setTransport(entity.getTransSelf());
				dto.setTranTemple(entity.getTransTemple());
				dto.setNewStudent(entity.getNewStudent());
				dto.setBangkok(entity.getBangkok());
				dto.setCentral(entity.getCenter());
				dto.setSakon(entity.getSakonnakhon());
				dto.setNorthEast(entity.getNortheast());
				dto.setNorth(entity.getNorth());
				dto.setEast(entity.getEast());
				dto.setWestern(entity.getWest());
				dto.setSouth(entity.getSouth());
			}
		} catch (Exception e) {
			log.error("mapEntityToDto >>> " + e.getMessage());
			e.printStackTrace();
		}
		return dto;
		
	}

}
