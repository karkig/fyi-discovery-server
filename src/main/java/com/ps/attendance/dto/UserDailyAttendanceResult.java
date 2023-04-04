package com.ps.attendance.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_attendance_result")
public class UserDailyAttendanceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private Integer noOfHours;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTime() {
        return noOfHours;
    }

    public void setTime(Integer time) {
        this.noOfHours = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
