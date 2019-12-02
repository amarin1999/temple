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
@Table(name = "transportations")
@Embeddable
public class TransportationEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "tran_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transportationId;

	@Column(name = "tran_name")
	private String transportationName;
	
	@Column(name = "tran_status")
	private boolean transportationStatus = true;

	public Long getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(Long transportationId) {
		this.transportationId = transportationId;
	}

	public String getTransportationName() {
		return transportationName;
	}

	public void setTransportationName(String transportationName) {
		this.transportationName = transportationName;
	}

	public boolean isTransportationStatus() {
		return transportationStatus;
	}

	public void setTransportationStatus(boolean transportationStatus) {
		this.transportationStatus = transportationStatus;
	}
	
	
}
