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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transportations")
@Embeddable
public class TransportationEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8609326581235221457L;

	@Id
	@Column(name = "tran_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transportationId;

	@Column(name = "tran_name")
	private String transportationName;
	
	@Column(name = "tran_time_id")
	private Long transportationTimeId;
	
	@Column(name = "course_id")
	private Long transportationCoursesId;

	@OneToOne
	@JoinColumn(name = "tran_time_id", insertable = false, updatable = false)
	private TransportationTimeEntity transportationTimeEntity;
	
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private CourseEntity coursesEntity;

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

	public TransportationTimeEntity getTransportationTimeEntity() {
		return transportationTimeEntity;
	}

	public void setTransportationTimeEntity(TransportationTimeEntity transportationTimeEntity) {
		this.transportationTimeEntity = transportationTimeEntity;
	}

	public CourseEntity getCoursesEntity() {
		return coursesEntity;
	}

	public void setCoursesEntity(CourseEntity coursesEntity) {
		this.coursesEntity = coursesEntity;
	}

	public Long getTransportationTimeId() {
		return transportationTimeId;
	}

	public void setTransportationTimeId(Long transportationTimeId) {
		this.transportationTimeId = transportationTimeId;
	}

	public Long getTransportationCoursesId() {
		return transportationCoursesId;
	}

	public void setTransportationCoursesId(Long transportationCoursesId) {
		this.transportationCoursesId = transportationCoursesId;
	}
	
	

}
