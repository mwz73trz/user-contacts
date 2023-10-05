package com.techelevator.model;


import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Office {
    private int officeId;

    private String  officeName;

    private String PhoneNumber;

    private Time openTime;

    private Time closeTime;

    private String address;

    private String city;
    private String state;
    private String zip;

    private BigDecimal serviceFee;

    private List<Employee> employees;



    public Office() {
    }

    public Office(int officeId, String officeName, String phoneNumber, Time openTime, Time closeTime, String address, String city, String state, String zip, BigDecimal serviceFee) {
        this.officeId = officeId;
        this.officeName = officeName;
        PhoneNumber = phoneNumber;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.serviceFee = serviceFee;
    }
    //Setters


    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    //Getters

    public int getOfficeId() {
        return officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
