package com.cdgs.temple.service;

import java.util.List;
import com.cdgs.temple.dto.ReportGenDto;

public interface ReportGenService {

	List<ReportGenDto> getAllDataReport();
	
	List<ReportGenDto> getDataReportByCourseId(Long courseId);
	
	ReportGenDto getReportDashboardMonkData();
	
	List<ReportGenDto> findCourseName();

	ReportGenDto getReportDashboardUserData(Long memberId);

}
