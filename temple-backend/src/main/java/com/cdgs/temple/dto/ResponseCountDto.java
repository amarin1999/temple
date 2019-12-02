package com.cdgs.temple.dto;

import java.io.Serializable;

public class ResponseCountDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4708969530621243531L;
	private int totalRecord;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
