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
@Table(name = "transportations_time")
@Embeddable
public class TransportationTimeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2044543602178920317L;

	@Id
	@Column(name = "tran_time_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transportationTimeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tran_time_pickup")
	private Date transportationTempleTimePickup;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tran_time_send")
	private Date transportationTempleTimeSend;

	public Long getTransportationTimeId() {
		return transportationTimeId;
	}

	public void setTransportationTimeId(Long transportationTimeId) {
		this.transportationTimeId = transportationTimeId;
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
