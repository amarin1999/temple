package com.cdgs.temple.dto;

import java.io.Serializable;

public class ReportGenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8200068162138826880L;

	private Long coursesId;
	private String coursesName;
	private Long genderMale;
	private Long genderFemale;
	/*** gender that not specified. * */
	private Long genderNotspec;
	private Long tranTemple;
	private Long transport;
	private Long newStudent;
	private Long bangkok;
	private Long central;
	/*** จังหวัด สกลนคร * */
	private Long sakon;
	private Long northEast;
	private Long north;
	private Long south;
	private Long east;
	private Long western;
	
	
	public Long getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(Long coursesId) {
		this.coursesId = coursesId;
	}
	
	public String getCoursesName() {
		return coursesName;
	}

	public void setCoursesName(String coursesName) {
		this.coursesName = coursesName;
	}
	public Long getGenderMale() {
		return genderMale;
	}

	public void setGenderMale(Long genderMale) {
		this.genderMale = genderMale;
	}

	public Long getGenderFemale() {
		return genderFemale;
	}

	public void setGenderFemale(Long genderFemale) {
		this.genderFemale = genderFemale;
	}

	public Long getGenderNotspec() {
		return genderNotspec;
	}

	public void setGenderNotspec(Long genderNotspec) {
		this.genderNotspec = genderNotspec;
	}

	public Long getTranTemple() {
		return tranTemple;
	}

	public void setTranTemple(Long tranTemple) {
		this.tranTemple = tranTemple;
	}

	public Long getTransport() {
		return transport;
	}

	public void setTransport(Long transport) {
		this.transport = transport;
	}

	public Long getNewStudent() {
		return newStudent;
	}

	public void setNewStudent(Long newStudent) {
		this.newStudent = newStudent;
	}

	public Long getBangkok() {
		return bangkok;
	}

	public void setBangkok(Long bangkok) {
		this.bangkok = bangkok;
	}

	public Long getCentral() {
		return central;
	}

	public void setCentral(Long central) {
		this.central = central;
	}

	public Long getSakon() {
		return sakon;
	}

	public void setSakon(Long sakon) {
		this.sakon = sakon;
	}

	public Long getNorthEast() {
		return northEast;
	}

	public void setNorthEast(Long northEast) {
		this.northEast = northEast;
	}

	public Long getNorth() {
		return north;
	}

	public void setNorth(Long north) {
		this.north = north;
	}

	public Long getSouth() {
		return south;
	}

	public void setSouth(Long south) {
		this.south = south;
	}

	public Long getEast() {
		return east;
	}

	public void setEast(Long east) {
		this.east = east;
	}

	public Long getWestern() {
		return western;
	}

	public void setWestern(Long western) {
		this.western = western;
	}

}
