package com.cdgs.temple.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "lockers")

public class LockerEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4407068550942794862L;

    @Id
    @Column(name="locker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lockerId;
    
   
	@Column(name = "location_id")
    private Long locationId;

  
    @Column(name = "locker_number")
    private String lockerNumber;


    @Column(name = "locker_create_by")
    private Long lockerCreateBy;


    @Column(name = "locker_status")
    private char isActive;

    @Column(name = "locker_enable")
    private boolean enable;

    @Column(name = "locker_last_update")
    @CreationTimestamp
    private LocalDateTime lockerLastUpdate;

    @Column(name = "locker_create_date")
    @CreationTimestamp
    private LocalDateTime lockerCreateDate;


    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private LocationEntity location;

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

    public String getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Long getLockerCreateBy() {
        return lockerCreateBy;
    }

    public void setLockerCreateBy(Long lockerCreateBy) {
        this.lockerCreateBy = lockerCreateBy;
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

    public LocalDateTime getLockerLastUpdate() {
        return lockerLastUpdate;
    }

    public void setLockerLastUpdate(LocalDateTime lockerLastUpdate) {
        this.lockerLastUpdate = lockerLastUpdate;
    }

    public LocalDateTime getLockerCreateDate() {
        return lockerCreateDate;
    }

    public void setLockerCreateDate(LocalDateTime lockerCreateDate) {
        this.lockerCreateDate = lockerCreateDate;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    @Override
	public String toString() {
		return "LockerEntity [lockerId=" + lockerId + ", locationId=" + locationId + ", lockerNumber=" + lockerNumber
				+ ", lockerCreateBy=" + lockerCreateBy + ", isActive=" + isActive + ", enable=" + enable
				+ ", lockerLastUpdate=" + lockerLastUpdate + ", lockerCreateDate=" + lockerCreateDate + ", location="
				+ location + "]";
	}
}
