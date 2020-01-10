package com.cdgs.temple.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GraduatedCourseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128133865181964921L;

	@Id
	private Long courseId;
	private String courseName;
	private Long countMember;
	private String courseDetail;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCountMember() {
		return countMember;
	}

	public void setCountMember(Long countMember) {
		this.countMember = countMember;
	}

	public String getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}

}
