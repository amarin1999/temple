package com.cdgs.temple.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "courses")
@Embeddable
public class TempCourseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1615306482250637476L;

	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	@Column(name = "course_no")
	private Long courseNo;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "st_date")
	private Date stDate;

	@Column(name = "course_detail")
	private String courseDetail;

	@Column(name = "course_condition_min")
	private int courseConditionMin;

	@Column(name = "course_location_id")
	private Long courseLocationId;

	@Column(name = "course_create_by")
	private Long courseCreateBy;

	@Column(name = "course_create_date")
	@CreationTimestamp
	private Date courseCreateDate;

	@Column(name = "course_last_update")
	@CreationTimestamp
	private Date courseLastUpdate;
//
//	@Column(name = "transportation_id")
//	private Long transportationId;

	@ManyToOne
	@JoinColumn(name = "course_create_by", insertable = false, updatable = false)
	private MemberEntity createBy;

	@ManyToOne
	@JoinColumn(name = "course_location_id", insertable = false, updatable = false)
	private LocationEntity locationId;

	private String mhcStatus;
	private String saStatus;
	private String statusText;
	private int canRegister;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(Long courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public String getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}

	public int getCourseConditionMin() {
		return courseConditionMin;
	}

	public void setCourseConditionMin(int courseConditionMin) {
		this.courseConditionMin = courseConditionMin;
	}

	public Long getCourseLocationId() {
		return courseLocationId;
	}

	public void setCourseLocationId(Long courseLocationId) {
		this.courseLocationId = courseLocationId;
	}

	public Long getCourseCreateBy() {
		return courseCreateBy;
	}

	public void setCourseCreateBy(Long courseCreateBy) {
		this.courseCreateBy = courseCreateBy;
	}

	public Date getCourseCreateDate() {
		return courseCreateDate;
	}

	public void setCourseCreateDate(Date courseCreateDate) {
		this.courseCreateDate = courseCreateDate;
	}

	public Date getCourseLastUpdate() {
		return courseLastUpdate;
	}

	public void setCourseLastUpdate(Date courseLastUpdate) {
		this.courseLastUpdate = courseLastUpdate;
	}

	public MemberEntity getCreateBy() {
		return createBy;
	}

	public void setCreateBy(MemberEntity createBy) {
		this.createBy = createBy;
	}

	public LocationEntity getLocationId() {
		return locationId;
	}

	public void setLocationId(LocationEntity locationId) {
		this.locationId = locationId;
	}

	public String getMhcStatus() {
		return mhcStatus;
	}

	public void setMhcStatus(String mhcStatus) {
		this.mhcStatus = mhcStatus;
	}

	public String getSaStatus() {
		return saStatus;
	}

	public void setSaStatus(String saStatus) {
		this.saStatus = saStatus;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public int getCanRegister() {
		return canRegister;
	}

	public void setCanRegister(int canRegister) {
		this.canRegister = canRegister;
	}
//
//	public Long getTransportationId() {
//		return transportationId;
//	}
//
//	public void setTransportationId(Long transportationId) {
//		this.transportationId = transportationId;
//	}

}
