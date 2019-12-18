package com.cdgs.temple.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transportation_temple")
@Embeddable
public class TransportationTempleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transportation_temple_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transportationTempleId;

	@Column(name = "transportation_temple_name")
	private String transportationTempleName;
	
	@Column(name = "transportation_temple_status")
	private boolean transportationTempleStatus = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transportation_temple_time_pickup")
	private Date transportationTempleTimePickup;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transportation_temple_time_send")
	private Date transportationTempleTimeSend;

	public Long getTransportationTempleId() {
		return transportationTempleId;
	}

	public void setTransportationTempleId(Long transportationTempleId) {
		this.transportationTempleId = transportationTempleId;
	}

	public String getTransportationTempleName() {
		return transportationTempleName;
	}

	public void setTransportationTempleName(String transportationTempleName) {
		this.transportationTempleName = transportationTempleName;
	}

	public boolean isTransportationTempleStatus() {
		return transportationTempleStatus;
	}

	public void setTransportationTempleStatus(boolean transportationTempleStatus) {
		this.transportationTempleStatus = transportationTempleStatus;
	}

	public Date getTransportationTempleTimePickup() {
		return transportationTempleTimePickup;
	}

	public void setTransportationTempleTimePickup(Date transportationTempleTimePickup) {
		this.transportationTempleTimePickup = transportationTempleTimePickup;
	}

	public Date getTransportationTempleTimeSend() {
		return transportationTempleTimeSend;
	}

	public void setTransportationTempleTimeSend(Date transportationTempleTimeSend) {
		this.transportationTempleTimeSend = transportationTempleTimeSend;
	}
	
	

}
