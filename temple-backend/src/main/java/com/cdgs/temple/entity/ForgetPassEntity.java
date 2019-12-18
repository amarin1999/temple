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
	@Column(name = "member_username")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long memberUsername;
	
	@Column(name = "member_id_card")
	private String memberIdCard;
	
	@Column(name = "member_tel")
	private String memberTel;

	public Long getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(Long memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getMemberIdCard() {
		return memberIdCard;
	}

	public void setMemberIdCard(String memberIdCard) {
		this.memberIdCard = memberIdCard;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
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
