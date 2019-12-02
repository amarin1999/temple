package com.cdgs.temple.entity;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "courses_teacher")
@IdClass(CourseTeacherEntity.class)
public class CourseTeacherEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8792828341834437997L;
	
	@Id
	@Column(name = "course_id")
	private Long courseId;

	@Id
	@Column(name = "member_id")
	private Long memberId;
	
//	@OneToOne
//    @JoinColumn(name = "member_id", insertable = false, updatable = false)
//    private MemberEntity member;

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

	@Override
	public String toString() {
		return "CourseTeacherEntity [courseId=" + courseId + ", memberId=" + memberId + "]";
	}

//	public MemberEntity getMember() {
//		return member;
//	}
//
//	public void setMember(MemberEntity member) {
//		this.member = member;
//	}


	
	
	
	
}
