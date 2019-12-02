package com.cdgs.temple.dto;

import java.io.Serializable;
import java.sql.Date;

public class ApprovalCoursesDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5875499638883264049L;
	private Long id;
	private String name;
	private int conditionMin;
	private Date stDate;
	private Date endDate;
	private String detail;
    private int numberOfMembers;
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
	public int getConditionMin() {
		return conditionMin;
	}
	public void setConditionMin(int conditionMin) {
		this.conditionMin = conditionMin;
	}
	public Date getStDate() {
		return stDate;
	}
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
    
	
}
