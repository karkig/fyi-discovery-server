package com.ps.attendance.service;

import com.ps.attendance.dao.UserDailyAttendanceStatus;

public interface AttendanceTrackerService {
    public boolean cardSwap(UserDailyAttendanceStatus request);

    public boolean calculateTotalHours();
}
