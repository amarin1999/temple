package com.cdgs.temple.dto;

import java.io.Serializable;

public class TransportationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5583529121863627541L;

	private Long id;
	private String name;
	private boolean status;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
