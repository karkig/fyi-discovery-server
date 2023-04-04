package com.ps.attendance.Controller;

import com.ps.attendance.dao.UserDailyAttendanceStatus;
import com.ps.attendance.service.AttendanceTrackerService;
import com.ps.attendance.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api")
public class AttendanceTrackerController {



    @Autowired
    AttendanceTrackerService attendanceTrackerService;


    @PostMapping(value = "/swap-out")
    public Boolean cardSwapOut(@RequestParam("userId") String userId) {
        UserDailyAttendanceStatus attendanceStatus = new UserDailyAttendanceStatus();

        attendanceStatus.setOutTime(Utils.getCurrentDateTime());
        attendanceStatus.setUserId(userId);
        attendanceStatus.setDate(Utils.getCurrentDateTime());

        return attendanceTrackerService.cardSwap(attendanceStatus);
    }


    @PostMapping(value = "/swap-in")
    public Boolean cardSwapIn(@RequestParam("userId") String userId) {
        UserDailyAttendanceStatus attendanceStatus = new UserDailyAttendanceStatus();
        attendanceStatus.setInTime(Utils.getCurrentDateTime());
        attendanceStatus.setDate(Utils.getCurrentDateTime());
        attendanceStatus.setUserId(userId);
        return attendanceTrackerService.cardSwap(attendanceStatus);
    }

    @GetMapping(value = "/total-hours")
    public boolean getTotalHours() {
        return attendanceTrackerService.calculateTotalHours();
    }
}
