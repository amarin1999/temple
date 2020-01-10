package com.cdgs.temple.dto;

import java.io.Serializable;

public class CourseTeacherDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -343434631679054967L;

	private Long courseId;
	private Long memberId;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
