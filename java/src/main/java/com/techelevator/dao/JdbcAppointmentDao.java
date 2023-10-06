package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Appointment;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAppointmentDao implements AppointmentDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;


    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public List<Appointment> getAppointmentByUser(Principal principal) {
        List <Appointment> appointment = new ArrayList<>();
        String sql = "SELECT appointment_id, created_time, patient_id, employee_id, appointment_date, start_time, end_time " +
                     "FROM appointment " +
                     "WHERE employee_id = ? ; ";

        User user = userDao.getUserByUsername(principal.getName());
        int employeeId = user.getId();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);
            while (results.next()) {
                appointment.add(mapRowToAppointment(results));
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return appointment;
    }


//    @Override
//    public List<Appointment> getAllAppointments() {
//        List <Appointment> appointment = new ArrayList<>();
//        String sql = "SELECT appointment_id, created_time, patient_id, employee_id, appointment_date, start_time, end_time " +
//                     "FROM appointment;";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//            while (results.next()) {
//                appointment.add(mapRowToAppointment(results));
//            }
//        } catch (CannotGetJdbcConnectionException ex) {
//            throw new DaoException("Unable to connect to server or database", ex);
//        }
//        return appointment;
//    }

    //Patient creates a new appointmentposyr
    @Override
    public Appointment createNewAppointment(Principal principal, Appointment appointment) {
       String sql = "INSERT INTO appointment( patient_id, employee_id, appointment_date, start_time, end_time) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING appointment_id; " ;
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                                                    userId,
                                                    appointment.getEmployeeId(),
                                                    appointment.getAppointmentDate(),
                                                    appointment.getStartTime(),
                                                    appointment.getEndTime());
        appointment.setAppointmentId(newId);
        return appointment;
    }

    private Appointment mapRowToAppointment(SqlRowSet rs){
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setCreatedTime(rs.getDate("created_time"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setEmployeeId(rs.getInt("employee_id"));
        appointment.setAppointmentDate(rs.getDate("appointment_date"));
        appointment.setStartTime(rs.getTime("start_time"));
        appointment.setEndTime(rs.getTime("end_time"));
        return appointment;
    }
}
