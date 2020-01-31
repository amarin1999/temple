package com.cdgs.temple.dto;

import java.util.Date;

public class NotificationsDto {
	private Long specialApproveID;
	private Long courseID;
	private String specialApproveStatus;
	//คนที่จะแจ้งเตือน
	private Long memberID;
	private String detail;
	private Long notificationStatus;
	private Date notificationTime;
	
	public NotificationsDto() {
		super();
	}

	public NotificationsDto(Long specialApproveID, Long courseID, String specialApproveStatus, Long memberID,
			String detail, Long notificationStatus, Date notificationTime) {
		super();
		this.specialApproveID = specialApproveID;
		this.courseID = courseID;
		this.specialApproveStatus = specialApproveStatus;
		this.memberID = memberID;
		this.detail = detail;
		this.notificationStatus = notificationStatus;
		this.notificationTime = notificationTime;
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

	public String getSpecialApproveStatus() {
		return specialApproveStatus;
	}

	public void setSpecialApproveStatus(String specialApproveStatus) {
		this.specialApproveStatus = specialApproveStatus;
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
