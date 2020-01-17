package com.cdgs.temple.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

@Entity
public class TempSpecialApproveEntity implements Serializable {
	private static final long serialVersionUID = -4780915762853262556L;

	@Id
	private long specialApproveId;
	private long memberId;
	private String spaDetail;
	private String displayName;
	
	@Transient
	private String courseName;
	
	@Transient
	private String courseDetail;
	
	@Transient
	private Date courseStDate;
	
	@Transient
	private Date courseEndDate;
	private String transportation;

	public long getSpecialApproveId() {
		return specialApproveId;
	}

	public void setSpecialApproveId(long specialApproveId) {
		this.specialApproveId = specialApproveId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSpaDetail() {
		return spaDetail;
	}

	public void setSpaDetail(String spaDetail) {
		this.spaDetail = spaDetail;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}

	public Date getCourseStDate() {
		return courseStDate;
	}

	public void setCourseStDate(Date courseStDate) {
		this.courseStDate = courseStDate;
	}

	public Date getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
}
