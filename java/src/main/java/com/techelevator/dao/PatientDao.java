package com.techelevator.dao;

import com.techelevator.model.Patient;

import java.security.Principal;
import java.util.List;


public interface PatientDao {

    List<Patient> getAllPatient();
    Patient getPatientByUser(String username) ;
    Patient createPatientInfo(Principal principal, Patient patient);



}
