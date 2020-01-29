package com.cdgs.temple.dto;

import java.io.Serializable;
import java.util.Date;

import com.cdgs.temple.entity.CourseEntity;
import com.cdgs.temple.entity.MemberEntity;
import com.cdgs.temple.entity.SpecialApproveEntity;

public class NotificationsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 394603069323208813L;
	
	private int notificationID;
	private SpecialApproveEntity specialApproveID;
	private CourseEntity courseID;
	private MemberEntity memberID;
	private long notificationStatus;
	private Date notificationTime;
	
	public NotificationsDto(int notificationID, SpecialApproveEntity specialApproveID, CourseEntity courseID,
			MemberEntity memberID, long notificationStatus, Date notificationTime) {
		super();
		this.notificationID = notificationID;
		this.specialApproveID = specialApproveID;
		this.courseID = courseID;
		this.memberID = memberID;
		this.notificationStatus = notificationStatus;
		this.notificationTime = notificationTime;
	}

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public SpecialApproveEntity getSpecialApproveID() {
		return specialApproveID;
	}

	public void setSpecialApproveID(SpecialApproveEntity specialApproveID) {
		this.specialApproveID = specialApproveID;
	}

	public CourseEntity getCourseID() {
		return courseID;
	}

	public void setCourseID(CourseEntity courseID) {
		this.courseID = courseID;
	}

	public MemberEntity getMemberID() {
		return memberID;
	}

	public void setMemberID(MemberEntity memberID) {
		this.memberID = memberID;
	}

	public long getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(long notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Date getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}
}
