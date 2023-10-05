package com.techelevator.controller;

import com.techelevator.dao.JdbcScheduleDao;
import com.techelevator.dao.ScheduleDao;
import com.techelevator.model.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ScheduleController {

    private ScheduleDao scheduleDao;

    public ScheduleController(JdbcScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/schedules/{employeeId}")
    public Schedule getScheduleByEmployeeId(@PathVariable int employeeId) {
        Schedule result = scheduleDao.getScheduleByEmployeeId(employeeId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/schedules")
    public Schedule createSchedule(@RequestBody Schedule newSchedule) {
        return scheduleDao.createSchedule(newSchedule.getEmployeeId(), newSchedule);
    }

    @PutMapping("/schedules/{scheduleId}")
    public Schedule updateSchedule(@PathVariable int scheduleId, @RequestBody Schedule updatedSchedule) {
        updatedSchedule.setScheduleId(scheduleId);
        if (scheduleDao.updateSchedule(updatedSchedule)) {
            return updatedSchedule;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found to update");
        }
    }
}
