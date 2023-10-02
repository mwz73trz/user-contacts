package com.techelevator.model;

public class Patient {
    private int patientId;

    private  String firstName;

    private String lastName;

    private int userId;

    public Patient() {
    }

    public Patient(int patientId, String firstName, String lastName, int userId) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    //Setters
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //Getters

    public int getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserId() {
        return userId;
    }
}
