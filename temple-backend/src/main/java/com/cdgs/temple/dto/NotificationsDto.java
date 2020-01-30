package com.cdgs.temple.dto;

import java.util.Date;

public class NotificationsDto {
	private int notificationID;
	private Long specialApproveID;
	private Long courseID;
	//คนที่จะแจ้งเตือน
	private Long memberID;
	private String detail;
	private Long notificationStatus;
	private Date notificationTime;
	
	public NotificationsDto() {
		super();
	}

	public NotificationsDto(int notificationID, Long specialApproveID, Long courseID, Long memberID, String detail,
			Long notificationStatus, Date notificationTime) {
		super();
		this.notificationID = notificationID;
		this.specialApproveID = specialApproveID;
		this.courseID = courseID;
		this.memberID = memberID;
		this.detail = detail;
		this.notificationStatus = notificationStatus;
		this.notificationTime = notificationTime;
	}

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public Long getSpecialApproveID() {
		return specialApproveID;
	}

	public void setSpecialApproveID(Long specialApproveID) {
		this.specialApproveID = specialApproveID;
	}

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}

	public Long getMemberID() {
		return memberID;
	}

	public void setMemberID(Long memberID) {
		this.memberID = memberID;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(Long notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Date getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}
}
