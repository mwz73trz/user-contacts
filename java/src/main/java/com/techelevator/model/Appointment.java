package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Appointment {

    private int appointmentId;
    private Timestamp createdTime;
    private int patientId;
    private int employee_id;
    private Date appointmentDate;
    private Time startTime;
    private Time endTime;

    public Appointment(){

    }
    public Appointment(int appointmentId, Timestamp createdTime, int patientId, int employee_id, Date appointmentDate, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.createdTime = createdTime;
        this.patientId = patientId;
        this.employee_id = employee_id;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
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
