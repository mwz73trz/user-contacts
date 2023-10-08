package com.techelevator.model;


import javax.xml.crypto.Data;

import java.sql.Time;
import java.util.Date;


public class Appointment {

    private int appointmentId;
    private Date createdTime;
    private int patientId;
    private int employeeId;
    private Date appointmentDateStart;
    private Date appointmentDateEnd;
    private Time appointmentTimeStart;
    private Time appointmentTimeEnd;

    public Appointment() {

    }

    public Appointment(int appointmentId, Date createdTime, int patientId, int employeeId, Date appointmentDateStart, Date appointmentDateEnd, Time appointmentTimeStart, Time appointmentTimeEnd) {
        this.appointmentId = appointmentId;
        this.createdTime = createdTime;
        this.patientId = patientId;
        this.employeeId = employeeId;
        this.appointmentDateStart = appointmentDateStart;
        this.appointmentDateEnd = appointmentDateEnd;
        this.appointmentTimeStart = appointmentTimeStart;
        this.appointmentTimeEnd = appointmentTimeEnd;
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

    public Date getAppointmentDateStart() {
        return appointmentDateStart;
    }

    public void setAppointmentDateStart(Date appointmentDateStart) {
        this.appointmentDateStart = appointmentDateStart;
    }

    public Date getAppointmentDateEnd() {
        return appointmentDateEnd;
    }

    public void setAppointmentDateEnd(Date appointmentDateEnd) {
        this.appointmentDateEnd = appointmentDateEnd;
    }

    public Time getAppointmentTimeStart() {
        return appointmentTimeStart;
    }

    public void setAppointmentTimeStart(Time appointmentTimeStart) {
        this.appointmentTimeStart = appointmentTimeStart;
    }

    public Time getAppointmentTimeEnd() {
        return appointmentTimeEnd;
    }

    public void setAppointmentTimeEnd(Time appointmentTimeEnd) {
        this.appointmentTimeEnd = appointmentTimeEnd;
    }
}