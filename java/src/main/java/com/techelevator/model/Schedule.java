package com.techelevator.model;

import java.sql.Time;

public class Schedule {

    private int scheduleId;
    private int employeeId;
    private Time startTime;
    private Time endTime;

    public Schedule() {}

    public Schedule(int scheduleId, int employeeId, Time startTime, Time endTime) {
        this.scheduleId = scheduleId;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
