package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

public class SpecialApproveDto implements Serializable {

	private static final long serialVersionUID = -6856109137261338018L;

	private long specialApproveId;
	private List<Long> spaId;
	private long memberId;
	private long courseId;
	private String detail;
	private String status;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdate;
	private String courseName;
	private String displayName;
	private long senseId;
	private String expected;
	private String experience;
	private long transportationId;
	private String transportationName;

	private Date stDate;
	private Date endDate;

	private List<LocalDate> date;

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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<Long> getSpaId() {
		return spaId;
	}

	public void setSpaId(List<Long> spaId) {
		this.spaId = spaId;
	}

	public long getSenseId() {
		return senseId;
	}

	public void setSenseId(long senseId) {
		this.senseId = senseId;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public long getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(long transportationId) {
		this.transportationId = transportationId;
	}

	public String getTransportationName() {
		return transportationName;
	}

	public void setTransportationName(String transportationName) {
		this.transportationName = transportationName;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<LocalDate> getDate() {
		return date;
	}

	public void setDate(List<LocalDate> date) {
		this.date = date;
	}

}
