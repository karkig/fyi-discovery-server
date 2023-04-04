package com.ps.attendance.repository;

import com.ps.attendance.dao.UserDailyAttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AttendanceRepository extends CrudRepository<UserDailyAttendanceStatus, Integer> {


    @Query(value = "SELECT MAX(out_time) as outTime, MIN(in_time) as inTime, date, user_id FROM user_attendance WHERE date = :date group by user_id", nativeQuery = true)
    public Iterable<Object[]> calculateTotalHours(Date date);

}
