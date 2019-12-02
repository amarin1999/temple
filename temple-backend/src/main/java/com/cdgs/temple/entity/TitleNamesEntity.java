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
@Table(name = "title_names")
@Embeddable
public class TitleNamesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3445764127909844707L;
	
	@Id
	@Column(name = "title_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long titleId;
	
	@Column(name = "title_display", unique = true)
	private String titleDisplay;
	
	@Column(name = "title_name", unique = true)
	private String titleName;
	
	

	public TitleNamesEntity() {
		super();
	}

	public TitleNamesEntity(Long titleId, String titleDisplay, String titleName) {
		super();
		this.titleId = titleId;
		this.titleDisplay = titleDisplay;
		this.titleName = titleName;
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getTitleDisplay() {
		return titleDisplay;
	}

	public void setTitleDisplay(String titleDisplay) {
		this.titleDisplay = titleDisplay;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	

}

