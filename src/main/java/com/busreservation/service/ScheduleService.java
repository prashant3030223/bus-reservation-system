package com.busreservation.service;

import com.busreservation.model.Schedule;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    void saveSchedule(Schedule schedule);
    Optional<Schedule> getScheduleById(Long id);
    void deleteScheduleById(Long id);
    long countSchedules();
    List<Schedule> findSchedulesBySourceAndDestination(String source, String destination);
}