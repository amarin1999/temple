package com.cdgs.temple.dto;

import java.io.Serializable;

import java.util.Date;
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
    private Date createDate;
    private Date lastUpdate;
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
    private List<Date> date;
    private TransportationDto transportation;
    
    public CourseDto() {
    	super();
    }

    public CourseDto(Long id, Long no, String name, Date stDate, Date endDate, String detail, int conditionMin,
			Date createDate, Date lastUpdate, Long memberId, String memberFname, String memberLname, Long locationId,
			String locationName, String mhcStatus, String saStatus, String status, int canRegister, List<Long> teacher,
			List<MemberDto> teacherList, List<CourseScheduleDto> dateList, List<Date> date,
			TransportationDto transportation) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.stDate = stDate;
		this.endDate = endDate;
		this.detail = detail;
		this.conditionMin = conditionMin;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.memberId = memberId;
		this.memberFname = memberFname;
		this.memberLname = memberLname;
		this.locationId = locationId;
		this.locationName = locationName;
		this.mhcStatus = mhcStatus;
		this.saStatus = saStatus;
		this.status = status;
		this.canRegister = canRegister;
		this.teacher = teacher;
		this.teacherList = teacherList;
		this.dateList = dateList;
		this.date = date;
		this.transportation = transportation;
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
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

	public List<Date> getDate() {
		return date;
	}

	public void setDate(List<Date> date) {
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

	public TransportationDto getTransportation() {
		return transportation;
	}

	public void setTransportation(TransportationDto transportation) {
		this.transportation = transportation;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ",no=" + no + ", name=" + name + ", stDate=" + stDate + ", endDate=" + endDate
				+ ", detail=" + detail + ", conditionMin=" + conditionMin + ", createDate=" + createDate
				+ ", lastUpdate=" + lastUpdate + ", memberId=" + memberId + ", memberFname=" + memberFname
				+ ", memberLname=" + memberLname + ", locationId=" + locationId + ", locationName=" + locationName
				+ ", mhcStatus=" + mhcStatus + ", saStatus=" + saStatus + ", status=" + status + ", canRegister="
				+ canRegister + ", teacher=" + teacher + ", teacherList=" + teacherList + ", dateList=" + dateList
				+ ", date=" + date + "]";
	}

}
