package com.cdgs.temple.service;

import java.util.List;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.dto.NotificationsDto;

public interface NotificationsService {
	void createNotifications(NotificationsDto body);
	List<CourseDto> getAllPreviouspast();
}
