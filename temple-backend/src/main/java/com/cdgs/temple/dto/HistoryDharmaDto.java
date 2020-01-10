package com.cdgs.temple.dto;

import java.io.Serializable;

public class HistoryDharmaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4907914194456485849L;

	private Long id;
	private String courseName;
	private String location;
	private Long memberId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
