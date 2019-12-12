package com.cdgs.temple.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sensations")
@Embeddable
public class SensationEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3971704619188825868L;
	
	@Id
    @Column(name = "sense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long senseId;

	/**
	 * ไม่ได้ใช้งาน Transportation แล้ว เนื่องจากย้ายไปเชื่อมกับตาราง Course หรือ Member Has Course โดยตรง
	 */
//    @Column(name = "tran_id")
//    private long TransportationId;

    @Column(name = "sense_expected")
    private String senseExpected;
    
    @Column(name = "sense_experience")
    private String senseExprience;
    
    
    public long getSenseId() {
		return senseId;
	}

	public void setSenseId(long senseId) {
		this.senseId = senseId;
	}

	public String getSenseExpected() {
		return senseExpected;
	}

	public void setSenseExpected(String senseExpected) {
		this.senseExpected = senseExpected;
	}

	public String getSenseExprience() {
		return senseExprience;
	}

	public void setSenseExprience(String senseExprience) {
		this.senseExprience = senseExprience;
	}
	

//	public long getTransportationId() {
//		return TransportationId;
//	}
//
//	public void setTransportationId(long transportationId) {
//		this.TransportationId = transportationId;
//	}
//
//
//
//	@ManyToOne
//    @JoinColumn(name = "tran_id", insertable = false, updatable = false)
//    private TransportationEntity transportation;
//
//	public TransportationEntity getTransportation() {
//		return transportation;
//	}
//
//	public void setTransportation(TransportationEntity transportation) {
//		this.transportation = transportation;
//	}
	
}
