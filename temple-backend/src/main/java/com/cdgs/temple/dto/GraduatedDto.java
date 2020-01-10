package com.cdgs.temple.dto;

import java.io.Serializable;
import java.util.List;

public class GraduatedDto implements Serializable {
	private static final long serialVersionUID = -8570567971745607790L;

	private Long uId;
	private Long cId;
	private Long mhcId;
	private String cName;
	private String fullname;
	private char status;
	private List<MemberHasCourseStatus> mhcList;

	public List<MemberHasCourseStatus> getMhcList() {
		return mhcList;
	}

	public void setMhcList(List<MemberHasCourseStatus> mhcList) {
		this.mhcList = mhcList;
	}

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Long getMhcId() {
		return mhcId;
	}

	public void setMhcId(Long mhcId) {
		this.mhcId = mhcId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GraduatedDto [uId=" + uId + ", cId=" + cId + ", mhcId=" + mhcId + ", cName=" + cName + ", fullname="
				+ fullname + ", status=" + status + ", mhcList=" + mhcList + "]";
	}

}