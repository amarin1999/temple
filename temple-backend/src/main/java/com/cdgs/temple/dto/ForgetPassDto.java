package com.cdgs.temple.dto;

import java.io.Serializable;

public class ForgetPassDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1853716198256100356L;
	private String username;
	private String idCard;
	private String phoneNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
