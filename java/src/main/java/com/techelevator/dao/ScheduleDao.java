package com.techelevator.dao;

import com.techelevator.model.Schedule;

public interface ScheduleDao {

    Schedule getScheduleByEmployeeId(int employeeId);
}
