package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LockerDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4621882744690940670L;

	private Long lockerId;

	private Long locationId;
	private String number;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdate;
	private char isActive;
	private boolean enable;
	private String locationName;
	private Long createBy;

	public Long getLockerId() {
		return lockerId;
	}

	public void setLockerId(Long lockerId) {
		this.lockerId = lockerId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return "LockerDto [lockerId=" + lockerId + ", locationId=" + locationId + ", number=" + number + ", createDate="
				+ createDate + ", lastUpdate=" + lastUpdate + ", isActive=" + isActive + ", enable=" + enable
				+ ", locationName=" + locationName + ", createBy=" + createBy + "]";
	}
}
