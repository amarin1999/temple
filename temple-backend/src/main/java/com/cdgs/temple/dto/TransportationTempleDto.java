package com.cdgs.temple.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransportationTempleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43402330612606769L;
	
	private Long id;
	private String name;
	private boolean status;
	private LocalDateTime timePickUp;
	private LocalDateTime timeSend;
	
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDateTime getTimePickUp() {
		return timePickUp;
	}
	public void setTimePickUp(LocalDateTime timePickUp) {
		this.timePickUp = timePickUp;
	}
	public LocalDateTime getTimeSend() {
		return timeSend;
	}
	public void setTimeSend(LocalDateTime timeSend) {
		this.timeSend = timeSend;
	}
	
	

}
