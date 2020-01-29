package com.cdgs.temple.service;

import com.cdgs.temple.dto.DashboardDto;

public interface DashboardService {

	DashboardDto getReportDashboardMonkData();

	DashboardDto getReportDashboardUserData(Long memberId);

}
