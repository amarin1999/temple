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
@Table(name = "locations")
@Embeddable
public class LocationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2667439266984772043L;

	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "location_enable")
	private boolean locationEnable = true;

	public boolean getLocationEnable() {
		return locationEnable;
	}

	public void setLocationEnable(boolean locationEnable) {
		this.locationEnable = locationEnable;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
