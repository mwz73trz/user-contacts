package com.techelevator.controller;

import com.techelevator.dao.JdbcPatientDao;
import com.techelevator.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.techelevator.model.Patient;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.dao.PatientDao;
import java.security.Principal;
@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")


public class PatientController {
    private PatientDao patientDao;


    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }
     @ResponseStatus(HttpStatus.OK)
     @RequestMapping(path = "patients/user", method = RequestMethod.GET)
    public Patient getPatient(Principal principal){
        Patient patient = null;
        patient = patientDao.getPatientByUser(principal.getName());
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No patient with that id.");
        } else {
            return patient;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/patient/form")
    public Patient postPatient(Principal principal, @RequestBody Patient newPatient) {
        return patientDao.createPatientInfo(principal, newPatient);
    }

}
