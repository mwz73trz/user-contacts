package com.techelevator.model;


import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;


public class Appointment {

    private int appointmentId;
    private Date createdTime;
    private int patientId;
    private int employeeId;
    private Date appointmentDate;
    private Time startTime;
    private Time endTime;

    public Appointment(){

    }

    public Appointment(int appointmentId, Date createdTime, int patientId, int employeeId, Date appointmentDate, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.patientId = patientId;
        this.employeeId = employeeId;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
