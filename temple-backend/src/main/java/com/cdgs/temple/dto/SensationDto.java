package com.cdgs.temple.dto;

import java.io.Serializable;

public class SensationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117276160740695867L;
	
	private Long id;
	private Long transportationId;
	private String expected;
	private String experience;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTransportationId() {
		return transportationId;
	}
	public void setTransportationId(Long transportationId) {
		this.transportationId = transportationId;
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

}
