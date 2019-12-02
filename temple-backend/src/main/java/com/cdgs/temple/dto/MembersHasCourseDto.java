package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class MembersHasCourseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8061410967659591652L;

	private long membersHasCourseId;

	private long memberId;
	private String title;
	private String fname;
	private String lname;
	private String tel;

	private long courseId;
	private char status;
	private LocalDateTime registerDate;
	private String courseName;
	private Date courseStDate;
	private Date courseEndDate;
	private long transportationId;
	private String experience;
	private String expected;
	private long senseId;

	public long getMembersHasCourseId() {
		return membersHasCourseId;
	}

	public void setMembersHasCourseId(long membersHasCourseId) {
		this.membersHasCourseId = membersHasCourseId;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public long getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(long transportationId) {
		this.transportationId = transportationId;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public long getSenseId() {
		return senseId;
	}

	public void setSenseId(long senseId) {
		this.senseId = senseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
