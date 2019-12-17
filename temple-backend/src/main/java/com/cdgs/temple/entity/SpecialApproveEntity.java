package com.cdgs.temple.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "special_approve")
@Embeddable
public class SpecialApproveEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4780915762853262556L;
	
	@Id
	@Column(name = "special_approve_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long specialApproveId;
	
	@Column(name = "member_id")
	private Long memberId;
	
	@Column(name = "course_id")
	private Long courseId;
	
	@Column(name = "spa_detail")
	private String spaDetail;
	
	@Column(name = "spa_status")
	private String spaStatus;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Column(name = "last_update")
	@CreationTimestamp
	private LocalDateTime lastUpdate;
	
	@Column(name = "sense_id")
	private Long senseId;

	public Long getSpecialApproveId() {
		return specialApproveId;
	}

	public void setSpecialApproveId(Long specialApproveId) {
		this.specialApproveId = specialApproveId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getSpaDetail() {
		return spaDetail;
	}

	public void setSpaDetail(String spaDetail) {
		this.spaDetail = spaDetail;
	}

	public String getSpaStatus() {
		return spaStatus;
	}

	public void setSpaStatus(String spaStatus) {
		this.spaStatus = spaStatus;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getSenseId() {
		return senseId;
	}

	public void setSenseId(Long senseId) {
		this.senseId = senseId;
	}
}
