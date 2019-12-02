package com.cdgs.temple.dto;

import java.io.Serializable;

public class TitleNameDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313985153088542242L;
	
	private Long id;
	private String name;
	private String display;
	
	
	
	
	public TitleNameDto() {
		super();
	}
	public TitleNameDto(Long id, String name, String display) {
		super();
		this.id = id;
		this.name = name;
		this.display = display;
	}
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
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name "+name+" display "+display;
	}

	
	
	
	

}
