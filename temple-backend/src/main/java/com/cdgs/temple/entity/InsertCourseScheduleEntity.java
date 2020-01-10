package com.cdgs.temple.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "courses_schedule")
@IdClass(InsertCourseScheduleEntity.class)
public class InsertCourseScheduleEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -470237690775417023L;

	@Id
	@Column(name = "course_id")
	private Long courseId;

	@Id
	@Column(name = "course_schedule_date")
	private Date courseScheduleDate;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Date getCourseScheduleDate() {
		return courseScheduleDate;
	}

	public void setCourseScheduleDate(Date courseScheduleDate) {
		this.courseScheduleDate = courseScheduleDate;
	}

}
