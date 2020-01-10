package com.cdgs.temple.dto;

import java.io.Serializable;

public class TeacherHasCourseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7246888756440534505L;

	private long courseId;
	private String name;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
