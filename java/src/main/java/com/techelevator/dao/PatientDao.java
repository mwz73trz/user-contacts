package com.techelevator.dao;

import com.techelevator.model.Patient;



public interface PatientDao {

    Patient getPatientByUser(String username) ;
    Patient createPatientInfo(Patient patient, int userId);



}
