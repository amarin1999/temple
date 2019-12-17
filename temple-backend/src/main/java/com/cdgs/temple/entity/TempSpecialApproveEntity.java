package com.cdgs.temple.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TempSpecialApproveEntity implements Serializable {
    private static final long serialVersionUID = -4780915762853262556L;

    @Id
    private long specialApproveId;
    private long memberId;
    private String displayName;
    private String spaDetail;
    private String transportation;

    public long getSpecialApproveId() {
        return specialApproveId;
    }

    public void setSpecialApproveId(long specialApproveId) {
        this.specialApproveId = specialApproveId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

	public String getSpaDetail() {
		return spaDetail;
	}

	public void setSpaDetail(String spaDetail) {
		this.spaDetail = spaDetail;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
}
