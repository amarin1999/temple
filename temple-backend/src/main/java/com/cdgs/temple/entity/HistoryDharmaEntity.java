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
import javax.persistence.Table;

@Entity
@Table(name = "history_dharma")
@Embeddable
public class HistoryDharmaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422669375047737226L;

	@Id
    @Column(name = "history_dharma_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyDharmaId;

    @Column(name = "history_dharma_desc")
    private String historyDharmaDesc;

    @Column(name = "history_dharma_location")
    private String historyDharmaLocation;
    
    @Column(name = "history_dharma_member_id")
	private Long historyDharmaMemberId;

	public long getHistoryDharmaId() {
		return historyDharmaId;
	}

	public void setHistoryDharmaId(long historyDharmaId) {
		this.historyDharmaId = historyDharmaId;
	}

	public String getHistoryDharmaDesc() {
		return historyDharmaDesc;
	}

	public void setHistoryDharmaDesc(String historyDharmaDesc) {
		this.historyDharmaDesc = historyDharmaDesc;
	}

	public String getHistoryDharmaLocation() {
		return historyDharmaLocation;
	}

	public void setHistoryDharmaLocation(String historyDharmaLocation) {
		this.historyDharmaLocation = historyDharmaLocation;
	}

	public Long getHistoryDharmaMemberId() {
		return historyDharmaMemberId;
	}

	public void setHistoryDharmaMemberId(Long historyDharmaMemberId) {
		this.historyDharmaMemberId = historyDharmaMemberId;
	}
	
}
