package com.cdgs.temple.dto;

import java.io.Serializable;

public class ProvinceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7647045371881029922L;
	private Long provinceId;
	private String provinceName;
	
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
