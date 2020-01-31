package com.cdgs.temple.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notifications")
@Embeddable
public class NotificationsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773860703664141240L;
	
	@Id
	@Column(name = "notification_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationID;
	
	@ManyToOne
	@JoinColumn(name = "special_approve_id")
	private SpecialApproveEntity specialApproveID;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity courseID;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private MemberEntity memberID;
	
	@Column(name = "notification_status")
	private long notificationStatus;
	
	@Temporal(TemporalType.TIME)
	private Date notificationTime;
	
	public NotificationsEntity() {
		super();
	}

	public NotificationsEntity(int notificationID, SpecialApproveEntity specialApproveID, CourseEntity courseID,
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