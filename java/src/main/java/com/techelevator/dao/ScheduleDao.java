package com.techelevator.dao;

import com.techelevator.model.Schedule;

public interface ScheduleDao {

    public Schedule getScheduleByEmployeeId(int employeeId);
    public Schedule createSchedule(int employeeId, Schedule schedule);
    public boolean updateSchedule(int scheduleId, Schedule schedule);

}
