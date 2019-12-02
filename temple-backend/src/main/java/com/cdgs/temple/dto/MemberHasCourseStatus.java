package com.cdgs.temple.dto;

import java.io.Serializable;

public class MemberHasCourseStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mhcId;
	private char status;
	
	public Long getMhcId() {
		return mhcId;
	}
	public void setMhcId(Long mhcId) {
		this.mhcId = mhcId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	

}
