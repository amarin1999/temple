package com.cdgs.temple.dto;

public class DashboardDto {
	private Long memberId;
	private Long genderMale;
	private Long genderFemale;
	/*** gender that not specified. **/
	private Long genderNotspec;
	private Long tranTemple;
	private Long transport;
	private Long central;
	/*** จังหวัด สกลนคร **/
	private Long northEast;
	private Long north;
	private Long south;
	private Long east;
	private Long western;
	/*** คอร์สที่ผ่าน **/
	private Long passCourse;
	/*** คอร์สที่กำลังศึกษา **/
	private Long studyCourse;
	private String province;
	private Long totalMemberHasCourse;
	
	public DashboardDto() {
		super();
	}

	public DashboardDto(Long memberId, Long genderMale, Long genderFemale, Long genderNotspec, Long tranTemple,
			Long transport, Long central, Long northEast, Long north, Long south, Long east, Long western,
			Long passCourse, Long studyCourse, String province, Long totalMemberHasCourse) {
		super();
		this.memberId = memberId;
		this.genderMale = genderMale;
		this.genderFemale = genderFemale;
		this.genderNotspec = genderNotspec;
		this.tranTemple = tranTemple;
		this.transport = transport;
		this.central = central;
		this.northEast = northEast;
		this.north = north;
		this.south = south;
		this.east = east;
		this.western = western;
		this.passCourse = passCourse;
		this.studyCourse = studyCourse;
		this.province = province;
		this.totalMemberHasCourse = totalMemberHasCourse;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public Long getCentral() {
		return central;
	}

	public void setCentral(Long central) {
		this.central = central;
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

	public Long getPassCourse() {
		return passCourse;
	}

	public void setPassCourse(Long passCourse) {
		this.passCourse = passCourse;
	}

	public Long getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(Long studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getTotalMemberHasCourse() {
		return totalMemberHasCourse;
	}

	public void setTotalMemberHasCourse(Long totalMemberHasCourse) {
		this.totalMemberHasCourse = totalMemberHasCourse;
	}
}
