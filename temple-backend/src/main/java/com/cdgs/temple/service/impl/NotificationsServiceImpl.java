package com.cdgs.temple.service.impl;

import java.util.Calendar;
import java.util.List;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.dto.NotificationsDto;
import com.cdgs.temple.dto.SpecialApproveDto;
import com.cdgs.temple.repository.MemberRepository;
import com.cdgs.temple.repository.NotificationsRepository;
import com.cdgs.temple.service.NotificationsService;
import com.cdgs.temple.util.FirebaseConnection;
import com.google.cloud.firestore.Firestore;

public class NotificationsServiceImpl implements NotificationsService {

	private final NotificationsRepository notificationsRepository = new NotificationsRepository();

	public NotificationsServiceImpl() {
		super();
	}

	@Override
	public void createMonkNotifications(List<Long> listTeacherId, Long specialApproveId, Long courseOutTimeId,
			String specialApproveStatus, String courseName) {
		try {
			for (Long teacherId : listTeacherId) {
				NotificationsDto notificationDto = new NotificationsDto();
				notificationDto.setSpecialApproveID(specialApproveId);
				notificationDto.setCourseID(courseOutTimeId);
				notificationDto.setMemberID(teacherId);
				notificationDto.setSpecialApproveStatus(specialApproveStatus);
				notificationDto.setNotificationStatus(Long.parseLong("0"));
				notificationDto.setDetail(courseOutTimeName);
				notificationDto.setNotificationTime(Calendar.getInstance().getTime());
				notificationsRepository.createUserNotification(notificationDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUserNotifications(Long userId, Long specialApproveId, Long courseId,
			String specialApproveStatus, String courseName) {
		try {
				NotificationsDto notificationDto = new NotificationsDto();
				notificationDto.setSpecialApproveID(specialApproveId);
				notificationDto.setCourseID(courseId);
				notificationDto.setMemberID(userId);
				notificationDto.setSpecialApproveStatus(specialApproveStatus);
				notificationDto.setNotificationStatus(Long.parseLong("0"));
				notificationDto.setDetail(courseOutTimeName);
				notificationDto.setNotificationTime(Calendar.getInstance().getTime());
				notificationsRepository.createUserNotification(notificationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
