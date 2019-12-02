package com.cdgs.temple.dto;

import java.io.Serializable;

public class GenderDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8763820756419086427L;
	
	private Long id ;
	private String name;
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
	
	

}
