package com.techelevator.model;

public class Employee {

    private int employeeId;
    private int userId;
    private String firstName;
    private String lastName;
    private int officeId;

    public Employee(){

    }
    public Employee(int employeeId, int userId, String firstName, String lastName, int officeId) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeId = officeId;
    }



    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
