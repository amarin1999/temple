package com.cdgs.temple.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "members_has_courses")
@Embeddable
public class MembersHasCourseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5345044781843738399L;

	@Id
	@Column(name = "members_has_course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long membersHasCourseId;

	@Column(name = "member_id")
	private long memberId;

	@Column(name = "course_id")
	private long courseId;

	@Column(name = "mhc_status")
	private char mhcStatus;

	@Column(name = "register_date")
	@CreationTimestamp
	private LocalDateTime registerDate;

	@Column(name = "sense_id")
	private long senseId;

	@ManyToOne
	@JoinColumn(name = "tran_id")
	private TransportationEntity tranId;

	@OneToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private CourseEntity course;

	@OneToOne
	@JoinColumn(name = "sense_id", insertable = false, updatable = false)
	private SensationEntity sense;

	public long getMembersHasCourseId() {
		return membersHasCourseId;
	}

	public void setMembersHasCourseId(long membersHasCourseId) {
		this.membersHasCourseId = membersHasCourseId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public char getMhcStatus() {
		return mhcStatus;
	}

	public void setMhcStatus(char mhcStatus) {
		this.mhcStatus = mhcStatus;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public long getSenseId() {
		return senseId;
	}

	public void setSenseId(long senseId) {
		this.senseId = senseId;
	}

	public TransportationEntity getTranId() {
		return tranId;
	}

	public void setTranId(TransportationEntity tranId) {
		this.tranId = tranId;
	}

	@Override
	public String toString() {
		return "MembersHasCourseEntity [membersHasCourseId=" + membersHasCourseId + ", memberId=" + memberId
				+ ", courseId=" + courseId + ", mhcStatus=" + mhcStatus + ", registerDate=" + registerDate + "]";
	}

}
