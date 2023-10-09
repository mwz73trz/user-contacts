package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class AppointmentController {

    private AppointmentDao appointmentDao;

    public AppointmentController(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/appointments", method = RequestMethod.GET)
    public List<Appointment> getAppointmentsForUser(Principal principal) {
        List<Appointment> appointments = appointmentDao.getAppointmentByUser(principal);
        if (appointments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointments found.");
        } else {
            return appointments;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/appointments/doctor", method = RequestMethod.GET)
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentDao.getAllAppointments();

        if (appointments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointments found.");
        } else {
            return appointments;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/appointments/{employeeId}", method = RequestMethod.GET)
    public List<Appointment> getAllAppointmentsByEmployeeId(@PathVariable int employeeId) {
        List<Appointment> appointments = appointmentDao.getAllAppointmentsForEmployee(employeeId);

        if (appointments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointments found.");
        } else {
            return appointments;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/appointments/create")
    public Appointment postAppointment(Principal principal, @RequestBody Appointment newAppointment) {
        return appointmentDao.createNewAppointmentByEmployee(principal, newAppointment);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/appointments/new")
    public Appointment postAppointmentByPatient(Principal principal, @RequestBody Appointment newAppointment) {
        return appointmentDao.createNewAppointmentByPatient(principal, newAppointment);
    }
}
