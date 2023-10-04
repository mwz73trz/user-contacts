package com.techelevator.model;

public class Patient {
    private int patientId;

    private  String firstName;

    private String lastName;


    public Patient() {
    }

    public Patient(int patientId, String firstName, String lastName) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
