package com.cdgs.temple.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
@Embeddable

public class ForgetPassEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7701296893488454698L;
	
	
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long memberId;
	
	@Column(name = "member_username", unique = true)
	private String memberUsername;
	
	@Column(name = "member_email")
	private String memberEmail;

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

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
//	@Id
//	@Column(name = "count")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer count;
//	
//	public Integer getCount() {
//		return count;
//	}
//	
//	public void setCount(Integer count) {
//		this.count = count;
//	}

}
