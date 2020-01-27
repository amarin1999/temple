package com.cdgs.temple.service;

import java.util.List;
import java.util.Map;

import com.cdgs.temple.dto.ReportGenDto;

public interface ReportGenService {

	List<ReportGenDto> getAllDataReport();
	
	List<ReportGenDto> getDataReportByCourseId(Long courseId);
	
	ReportGenDto getReportDashboardData();
	
	List<ReportGenDto> findCourseName();

}
