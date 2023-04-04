package com.ps.attendance.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.attendance.dao.UserDailyAttendanceStatus;
import com.ps.attendance.dto.UserDailyAttendanceResult;
import com.ps.attendance.repository.AttendanceRepository;
import com.ps.attendance.service.AttendanceTrackerService;
import com.ps.attendance.service.KafkaProducer;
import com.ps.attendance.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendanceTrackerServiceImpl implements AttendanceTrackerService {

    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public boolean cardSwap(UserDailyAttendanceStatus status) {
        UserDailyAttendanceStatus save = attendanceRepository.save(status);
        return save.getId() != null;
    }

    @Override
    public boolean calculateTotalHours() {

        Iterable<Object[]> userDailyAttendanceStatuses = attendanceRepository.calculateTotalHours(Utils.getCurrentDate());

        userDailyAttendanceStatuses.forEach(e -> {
            Date outTime = (Date) e[0];
            Date inTime = (Date) e[1];
            Date date = (Date) e[2];
            String userId = (String) e[3];

            int difference = Utils.calculateTimeDifference(Utils.convertDateToLocalDateTime(outTime), Utils.convertDateToLocalDateTime(inTime));
            UserDailyAttendanceResult result = new UserDailyAttendanceResult();
            result.setDate(date);
            result.setTime(difference);
            result.setUserId(userId);
            try {
                String json = objectMapper.writeValueAsString(result);
                sendMessage(json);
                System.out.println("Event sent " + json);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        });
        return true;
    }


    public boolean sendMessage(String message) {
        kafkaProducer.sendMessageToTopic(message);
        return true;
    }

}
