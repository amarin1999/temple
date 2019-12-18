package com.cdgs.temple.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 234203082606309582L;

    private Long id;
    private Long no;
    private String name;
    private Date stDate;
    private Date endDate;
    private String detail;
    private int conditionMin;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private Long memberId;
    private String memberFname;
    private String memberLname;
    private Long locationId;
    private String locationName;
    private String mhcStatus;
    private String saStatus;
    private String status;
    private int canRegister;
    private List<Long> teacher;
    private List<MemberDto> teacherList;
    private List<CourseScheduleDto> dateList;
    private List<LocalDate> date;
    private Long transportTempleId;
    private String transportTempleName;
    private Date transportTempleTimePickUp;
    private Date transportTempleTimeSend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getConditionMin() {
        return conditionMin;
    }

    public void setConditionMin(int conditionMin) {
        this.conditionMin = conditionMin;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberFname() {
        return memberFname;
    }

    public void setMemberFname(String memberFname) {
        this.memberFname = memberFname;
    }

    public String getMemberLname() {
        return memberLname;
    }

    public void setMemberLname(String memberLname) {
        this.memberLname = memberLname;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCanRegister() {
        return canRegister;
    }

    public void setCanRegister(int canRegister) {
        this.canRegister = canRegister;
    }

	public List<LocalDate> getDate() {
		return date;
	}

	public void setDate(List<LocalDate> date) {
		this.date = date;
	}

	public List<CourseScheduleDto> getDateList() {
		return dateList;
	}

	public void setDateList(List<CourseScheduleDto> dateList) {
		this.dateList = dateList;
	}

	public List<Long> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Long> teacher) {
		this.teacher = teacher;
	}

	public List<MemberDto> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<MemberDto> teacherList) {
		this.teacherList = teacherList;
	}

	public Long getTransportTempleId() {
		return transportTempleId;
	}

	public void setTransportTempleId(Long transportTempleId) {
		this.transportTempleId = transportTempleId;
	}
	
	public String getTransportTempleName() {
		return transportTempleName;
	}

	public void setTransportTempleName(String transportTempleName) {
		this.transportTempleName = transportTempleName;
	}

	public Date getTransportTempleTimePickUp() {
		return transportTempleTimePickUp;
	}

	public void setTransportTempleTimePickUp(Date transportTempleTimePickUp) {
		this.transportTempleTimePickUp = transportTempleTimePickUp;
	}

	public Date getTransportTempleTimeSend() {
		return transportTempleTimeSend;
	}

	public void setTransportTempleTimeSend(Date transportTempleTimeSend) {
		this.transportTempleTimeSend = transportTempleTimeSend;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ",no=" + no + ", name=" + name + ", stDate=" + stDate + ", endDate=" + endDate + ", detail="
				+ detail + ", conditionMin=" + conditionMin + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + ", memberId=" + memberId + ", memberFname=" + memberFname + ", memberLname="
				+ memberLname + ", locationId=" + locationId + ", locationName=" + locationName + ", mhcStatus="
				+ mhcStatus + ", saStatus=" + saStatus + ", status=" + status + ", canRegister=" + canRegister
				+ ", teacher=" + teacher + ", teacherList=" + teacherList + ", dateList=" + dateList + ", date=" + date 
				+ ", transportTempleId ="+ transportTempleId + ", transportTempleName =" + transportTempleName 
				+ ", transportTempleTimePickUp =" + transportTempleTimePickUp + ", transportTempleTimeSend ="
				+ transportTempleTimeSend + "]";
	}
	
	
	
	
	
	




}
