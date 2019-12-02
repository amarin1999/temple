package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaggageDto implements Serializable {

	private static final long serialVersionUID = -4621882744690940670L;

	private Long baggageId;
	private Long memberId;
	private Long lockerId;

	private char status;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdate;
	private String Locker;
	private String memberName;

	public Long getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(Long baggageId) {
		this.baggageId = baggageId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getLockerId() {
		return lockerId;
	}

	public void setLockerId(Long lockerId) {
		this.lockerId = lockerId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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



	public String getLocker() {
		return Locker;
	}

	public void setLocker(String locker) {
		Locker = locker;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "BaggageDto [baggageId=" + baggageId + ", memberId=" + memberId + ", lockerId=" + lockerId + ", status="
				+ status + ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + ", Locker=" + Locker
				+ ", memberName=" + memberName + "]";
	}
}
