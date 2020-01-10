package com.cdgs.temple.dto;

import java.io.Serializable;

public class MemberGraduated implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4572932467463397671L;
	private long memberId;
	private String fname;
	private String lname;
	private long courseId;
	private String status;

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
