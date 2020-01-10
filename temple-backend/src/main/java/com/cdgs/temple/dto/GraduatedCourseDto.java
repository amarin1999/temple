package com.cdgs.temple.dto;

import java.io.Serializable;

public class GraduatedCourseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111852761610608931L;
	private Long id;
	private String name;
	private String detail;
	private Long numberOfMembers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(Long numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

}
