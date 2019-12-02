package com.cdgs.temple.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LockerIdEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4407068550942794862L;

    protected Long locationId;
    protected String lockerNumber;

    public LockerIdEntity() {
    }

    public LockerIdEntity(Long locationId, String lockerNumber) {
        this.locationId = locationId;
        this.lockerNumber = lockerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LockerIdEntity that = (LockerIdEntity) o;
        return Objects.equals(locationId, that.locationId) &&
                Objects.equals(lockerNumber, that.lockerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, lockerNumber);
    }
}
