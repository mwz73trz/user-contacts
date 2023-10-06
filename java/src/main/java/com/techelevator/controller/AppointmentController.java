package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@PreAuthorize("isAuthenticated()")
public class AppointmentController {

    private AppointmentDao appointmentDao;

    public AppointmentController(AppointmentDao appointmentDao){
        this.appointmentDao = appointmentDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/appointments", method = RequestMethod.GET)
    public List<Appointment> getAppointmentsForUser(Principal principal){
        List<Appointment> appointments  = appointmentDao.getAppointmentByUser(principal);
        if (appointments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointments found.");
        } else {
            return appointments;
        }
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "/appointments", method = RequestMethod.GET)
//    public List<Appointment> getAppointments(){
//        List<Appointment> appointments = appointmentDao.getAllAppointments();
//
//        if (appointments.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointments found.");
//        } else {
//            return appointments;
//        }
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/appointment")
    public Appointment postAppointment(Principal principal, @RequestBody Appointment newAppointment) {
        return appointmentDao.createNewAppointment(principal, newAppointment);
    }
}
