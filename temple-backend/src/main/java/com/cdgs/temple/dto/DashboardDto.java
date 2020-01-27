package com.cdgs.temple.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DashboardDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, List<String>> gender;
	private Map<String, List<String>> trapotation;
	private Map<String, List<String>> region;
	
	public DashboardDto() {
		super();
	}

	public DashboardDto(Map<String, List<String>> gender, Map<String, List<String>> trapotation,
			Map<String, List<String>> region) {
		super();
		this.gender = gender;
		this.trapotation = trapotation;
		this.region = region;
	}

	public Map<String, List<String>> getGender() {
		return gender;
	}

	public void setGender(Map<String, List<String>> gender) {
		this.gender = gender;
	}

	public Map<String, List<String>> getTrapotation() {
		return trapotation;
	}

	public void setTrapotation(Map<String, List<String>> trapotation) {
		this.trapotation = trapotation;
	}

	public Map<String, List<String>> getRegion() {
		return region;
	}

	public void setRegion(Map<String, List<String>> region) {
		this.region = region;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
