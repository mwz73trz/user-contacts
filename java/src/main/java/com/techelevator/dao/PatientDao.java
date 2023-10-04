package com.techelevator.dao;

import com.techelevator.model.Patient;

import java.security.Principal;


public interface PatientDao {

    Patient getPatientByUser(String username) ;
    Patient createPatientInfo(Principal principal, Patient patient);



}
