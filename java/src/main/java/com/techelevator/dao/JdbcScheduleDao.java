package com.techelevator.dao;

import com.techelevator.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class JdbcScheduleDao implements ScheduleDao{
    @Override
    public Schedule getScheduleByEmployeeId(int employeeId) {
        Schedule schedule = null;
        String sql = ""
        return schedule;
    }
}
