package com.cdgs.temple.entity;

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
	
	@Column(name = "transportation_temple_time_pickup")
	private LocalDateTime transportationTempleTimePickup;
	
	@Column(name = "transportation_temple_time_send")
	private LocalDateTime transportationTempleTimeSend;

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

	public LocalDateTime getTransportationTempleTimePickup() {
		return transportationTempleTimePickup;
	}

	public void setTransportationTempleTimePickup(LocalDateTime transportationTempleTimePickup) {
		this.transportationTempleTimePickup = transportationTempleTimePickup;
	}

	public LocalDateTime getTransportationTempleTimeSend() {
		return transportationTempleTimeSend;
	}

	public void setTransportationTempleTimeSend(LocalDateTime transportationTempleTimeSend) {
		this.transportationTempleTimeSend = transportationTempleTimeSend;
	}
	
	

}
