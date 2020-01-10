package com.cdgs.temple.dto;

import java.io.Serializable;
import java.util.Date;

public class TransportationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5583529121863627541L;

	private Long id;
	private String name;
	private Long tranTimeId;
	private Date timePickUp;
	private Date timeSend;
	private Long courseId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTranTimeId() {
		return tranTimeId;
	}
	public void setTranTimeId(Long tranTimeId) {
		this.tranTimeId = tranTimeId;
	}
	public Date getTimePickUp() {
		return timePickUp;
	}
	public void setTimePickUp(Date timePickUp) {
		this.timePickUp = timePickUp;
	}
	public Date getTimeSend() {
		return timeSend;
	}
	public void setTimeSend(Date timeSend) {
		this.timeSend = timeSend;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
	
	
}
