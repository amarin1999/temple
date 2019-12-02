package com.cdgs.temple.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "courses_schedule")
@IdClass(CourseScheduleEntity.class)
public class CourseScheduleEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -470237690775417023L;

    @Id
    @Column(name = "course_id")
    private Long courseId;

    @Id
    @Column(name = "course_schedule_date")
    private LocalDate courseScheduleDate;
    
    
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getCourseScheduleDate() {
        return courseScheduleDate;
    }

    public void setCourseScheduleDate(LocalDate courseScheduleDate) {
        this.courseScheduleDate = courseScheduleDate;
    }
}
