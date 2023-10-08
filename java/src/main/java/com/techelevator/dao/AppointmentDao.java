package com.techelevator.dao;

import com.techelevator.model.Appointment;

import java.security.Principal;
import java.util.List;

public interface AppointmentDao {

    List<Appointment> getAppointmentByUser(Principal principal);

    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointmentsForEmployee(int employeeId);
    Appointment createNewAppointment(Principal principal, Appointment appointment);
}
