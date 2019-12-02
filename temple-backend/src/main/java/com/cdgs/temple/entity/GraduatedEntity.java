package com.cdgs.temple.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GraduatedEntity implements Serializable {


    private static final long serialVersionUID = 6171146455155425873L;
    @Id
    private Long membersHasCourseId;
    private Long memberId;
    private Long courseId;
    private String courseName;
    private String displayName;
    private char mhcStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getMemberHasCourseId() {
        return membersHasCourseId;
    }

    public void setMemberHasCourseId(Long memberHasCourseId) {
        this.membersHasCourseId = memberHasCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public char getMhcStatus() {
        return mhcStatus;
    }

    public void setMhcStatus(char mhcStatus) {
        this.mhcStatus = mhcStatus;
    }
}
