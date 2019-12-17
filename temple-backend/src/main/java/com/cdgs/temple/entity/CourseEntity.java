package com.cdgs.temple.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "courses")
@Embeddable
public class CourseEntity implements Serializable {

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

    @Column(name = "course_st_date")
    private Date courseStDate;

    @Column(name = "course_end_date")
    private Date courseEndDate;

    @Column(name = "course_detail")
    private String courseDetail;

    @Column(name = "course_condition_min")
    private int courseConditionMin;

    @Column(name = "course_location_id")
    private Long courseLocationId;
    
    @Column(name = "course_transportation_temple_id")
    private Long courseTransportTempleId;

    @Column(name = "course_create_by")
    private Long courseCreateBy;

    @Column(name = "course_create_date")
    @CreationTimestamp
    private LocalDateTime courseCreateDate;

    @Column(name = "course_last_update")
    @CreationTimestamp
    private LocalDateTime courseLastUpdate;
    
    @Column(name = "course_status")
    private String courseStatus;
    
    @Column(name = "course_enable")
    private boolean courseEnable = true;

    @ManyToOne
    @JoinColumn(name = "course_create_by", insertable = false, updatable = false)
    private MemberEntity createBy;

    @ManyToOne
    @JoinColumn(name = "course_location_id", insertable = false, updatable = false)
    private LocationEntity locationId;
    
    @ManyToOne
    @JoinColumn(name = "course_transportation_temple_id", insertable = false, updatable = false)
    private TransportationTempleEntity transportTempleId;
    
//    @Column(name = "transportation_temple_name")
//    private String transportTempleName;
//    
//    @Column(name = "transportation_temple_time_pickup")
//    private LocalDateTime transportTempleTimePickUp; 
//    
//    @Column(name = "transportation_temple_time_send")
//    private LocalDateTime transportTempleTimeSend;
    
    @OneToMany
    @JoinColumn(name = "course_id",insertable = false, updatable = false)
    private List<CourseScheduleEntity> courseSchdule;
    
    @OneToMany
    @JoinColumn(name = "course_id",insertable = false, updatable = false)
    private List<CourseTeacherEntity> courseTeacher;
    
    
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

    public Date getCourseStDate() {
        return courseStDate;
    }

    public void setCourseStDate(Date courseStDate) {
        this.courseStDate = courseStDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
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

    public LocalDateTime getCourseCreateDate() {
        return courseCreateDate;
    }

    public void setCourseCreateDate(LocalDateTime courseCreateDate) {
        this.courseCreateDate = courseCreateDate;
    }

    public LocalDateTime getCourseLastUpdate() {
        return courseLastUpdate;
    }

    public void setCourseLastUpdate(LocalDateTime courseLastUpdate) {
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

    public boolean isCourseEnable() {
		return courseEnable;
	}

	public void setCourseEnable(boolean courseEnable) {
		this.courseEnable = courseEnable;
	}

	public List<CourseScheduleEntity> getCourseSchdule() {
		return courseSchdule;
	}

	public void setCourseSchdule(List<CourseScheduleEntity> courseSchdule) {
		this.courseSchdule = courseSchdule;
	}

	public List<CourseTeacherEntity> getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(List<CourseTeacherEntity> courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public Long getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(Long courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Long getCourseTransportTempleId() {
		return courseTransportTempleId;
	}

	public void setCourseTransportTempleId(Long courseTransportTempleId) {
		this.courseTransportTempleId = courseTransportTempleId;
	}

	public TransportationTempleEntity getTransportTempleId() {
		return transportTempleId;
	}

	public void setTransportTempleId(TransportationTempleEntity transportTempleId) {
		this.transportTempleId = transportTempleId;
	}

	
	

	
	

	
}
