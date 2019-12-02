package com.cdgs.temple.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "members")
public class MemberEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6122881300354684546L;

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	
	@Column(name = "member_username", unique = true)
	private String memberUsername;

	@Column(name = "member_password")
	private String memberPassword;

	@Column(name = "member_fname")
	private String memberFname;

	@Column(name = "member_lname")
	private String memberLname;

	@Column(name = "member_address")
	private String memberAddress;

	@Column(name = "member_tel")
	private String memberTel;

	@Column(name = "member_emergency_tel")
	private String memberEmergencyTel;

	@Column(name = "member_email")
	private String memberEmail;

	@Lob
	@Column(name = "member_img")
	private byte[] memberImg;

	@Column(name = "member_register_date")
	@CreationTimestamp
	private LocalDateTime memberRegisterDate;

	@Column(name = "member_last_update")
	@CreationTimestamp
	private LocalDateTime memberLastUpdate;

	@Column(name = "member_gender_id")
	private Long memberGenderId;

	@Column(name = "member_role_id")
	private Long memberRoleId;

	@Column(name = "member_title_id")
	private Long memberTitleId;

	@Column(name = "member_job")
	private String memberJob;

	@Column(name = "member_other")
	private String memberOther;
	
	@Column(name = "member_blood")
	private String memberBlood;
	
	@Column(name = "member_allergy_food")
	private String memberAllergyFood;
	
	@Column(name = "member_allergy_medicine")
	private String memberAllergyMedicine;
	
	@Column(name = "member_disease")
	private String memberDisease;
	
	@Column(name = "member_emer_name")
	private String memberEmerName;
	
	@Column(name = "member_emer_relationship")
	private String memberEmerRelationship;
	

	@ManyToOne
	@JoinColumn(name = "member_gender_id", insertable = false, updatable = false)
	private GenderEntity gender;

	@ManyToOne
	@JoinColumn(name = "member_role_id", insertable = false, updatable = false)
	private RoleEntity role;

	@ManyToOne
	@JoinColumn(name = "member_title_id", insertable = false, updatable = false)
	private TitleNamesEntity titleName;
	
	
	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public String getMemberUsername() {
		return memberUsername;
	}


	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}


	public String getMemberPassword() {
		return memberPassword;
	}


	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
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


	public String getMemberAddress() {
		return memberAddress;
	}


	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}


	public String getMemberTel() {
		return memberTel;
	}


	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}


	public String getMemberEmergencyTel() {
		return memberEmergencyTel;
	}


	public void setMemberEmergencyTel(String memberEmergencyTel) {
		this.memberEmergencyTel = memberEmergencyTel;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public byte[] getMemberImg() {
		return memberImg;
	}


	public void setMemberImg(byte[] memberImg) {
		this.memberImg = memberImg;
	}


	public LocalDateTime getMemberRegisterDate() {
		return memberRegisterDate;
	}


	public void setMemberRegisterDate(LocalDateTime memberRegisterDate) {
		this.memberRegisterDate = memberRegisterDate;
	}


	public LocalDateTime getMemberLastUpdate() {
		return memberLastUpdate;
	}


	public void setMemberLastUpdate(LocalDateTime memberLastUpdate) {
		this.memberLastUpdate = memberLastUpdate;
	}


	public Long getMemberGenderId() {
		return memberGenderId;
	}


	public void setMemberGenderId(Long memberGenderId) {
		this.memberGenderId = memberGenderId;
	}


	public Long getMemberRoleId() {
		return memberRoleId;
	}


	public void setMemberRoleId(Long memberRoleId) {
		this.memberRoleId = memberRoleId;
	}


	public Long getMemberTitleId() {
		return memberTitleId;
	}


	public void setMemberTitleId(Long memberTitleId) {
		this.memberTitleId = memberTitleId;
	}


	public String getMemberJob() {
		return memberJob;
	}


	public void setMemberJob(String memberJob) {
		this.memberJob = memberJob;
	}


	public String getMemberOther() {
		return memberOther;
	}


	public void setMemberOther(String memberOther) {
		this.memberOther = memberOther;
	}


	public String getMemberBlood() {
		return memberBlood;
	}


	public void setMemberBlood(String memberBlood) {
		this.memberBlood = memberBlood;
	}


	public String getMemberAllergyFood() {
		return memberAllergyFood;
	}


	public void setMemberAllergyFood(String memberAllergyFood) {
		this.memberAllergyFood = memberAllergyFood;
	}


	public String getMemberAllergyMedicine() {
		return memberAllergyMedicine;
	}


	public void setMemberAllergyMedicine(String memberAllergyMedicine) {
		this.memberAllergyMedicine = memberAllergyMedicine;
	}


	public String getMemberDisease() {
		return memberDisease;
	}


	public void setMemberDisease(String memberDisease) {
		this.memberDisease = memberDisease;
	}


	public String getMemberEmerName() {
		return memberEmerName;
	}


	public void setMemberEmerName(String memberEmerName) {
		this.memberEmerName = memberEmerName;
	}


	public String getMemberEmerRelationship() {
		return memberEmerRelationship;
	}


	public void setMemberEmerRelationship(String memberEmerRelationship) {
		this.memberEmerRelationship = memberEmerRelationship;
	}

	public GenderEntity getGender() {
		return gender;
	}


	public void setGender(GenderEntity gender) {
		this.gender = gender;
	}


	public RoleEntity getRole() {
		return role;
	}


	public void setRole(RoleEntity role) {
		this.role = role;
	}


	public TitleNamesEntity getTitleName() {
		return titleName;
	}


	public void setTitleName(TitleNamesEntity titleName) {
		this.titleName = titleName;
	}


	@Override
	public String toString() {
		return "MemberEntity [memberId=" + memberId + ", memberUsername=" + memberUsername + ", memberPassword="
				+ memberPassword + ", memberFname=" + memberFname + ", memberLname=" + memberLname + ", memberAddress="
				+ memberAddress + ", memberTel=" + memberTel + ", memberEmergencyTel=" + memberEmergencyTel
				+ ", memberEmail=" + memberEmail + ", memberImg=" + memberImg + ", memberRegisterDate="
				+ memberRegisterDate + ", memberLastUpdate=" + memberLastUpdate + ", memberGenderId=" + memberGenderId
				+ ", memberRoleId=" + memberRoleId + ", memberTitleId=" + memberTitleId + ", gender=" + gender
				+ ", role=" + role + ", titleName=" + titleName + "]";
	}

}
