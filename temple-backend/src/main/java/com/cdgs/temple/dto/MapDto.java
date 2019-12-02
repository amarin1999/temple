package com.cdgs.temple.dto;

import java.io.Serializable;

public class MapDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1624843638255147242L;

	private Long id;
	private String username;
	private String genderName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

}
