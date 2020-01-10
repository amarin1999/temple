package com.cdgs.temple.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "members")

@SecondaryTable(name = "gender", pkJoinColumns = @PrimaryKeyJoinColumn(name = "gender_id"))
// pkJoinColumns = @PrimaryKeyJoinColumn(name = "gender_id",referencedColumnName = "member_gender_id")
public class MapEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6122881300354684546L;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "member_fname")
	private String memberFname;

	@Column(name = "member_lname")
	private String memberLname;

	@Id
	@Column(name = "member_gender_id")
	private Long memberGenderId;

	@Column(name = "gender_name", table = "gender")
	private String genderName;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberFname() {
		return memberFname;
	}

	public void setMemberFname(String memberFname) {
		this.memberFname = memberFname;
	}

	public String getMemberLname() {
		return memberLname;
	}

	public void setMemberLname(String memberLname) {
		this.memberLname = memberLname;
	}

	public Long getMemberGenderId() {
		return memberGenderId;
	}

	public void setMemberGenderId(Long memberGenderId) {
		this.memberGenderId = memberGenderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Override
	public String toString() {
		return "MapEntity [memberId=" + memberId + ", memberFname=" + memberFname + ", memberLname=" + memberLname
				+ ", memberGenderId=" + memberGenderId + ", genderName=" + genderName + "]";
	}

}
