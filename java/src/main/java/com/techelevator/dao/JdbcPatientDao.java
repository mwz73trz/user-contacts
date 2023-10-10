package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Patient;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPatientDao implements PatientDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcPatientDao(DataSource dataSource, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = userDao;
    }


    @Override
    public List<Patient> getAllPatient() {
        List <Patient> result = new ArrayList<>();
        String sql = "SELECT patient_id, first_name, last_name, " +
                        "address, city, state, zip, phone_number, email " +
                        " FROM patient ; " ;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        try {
            while(rowSet.next()) {
                Patient patient = mapRowToPatient(rowSet);
                result.add(patient);
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return result;
    }


    @Override
    public Patient getPatientByUser(String username) {
        Patient patient = null;
        String sql = "SELECT patient.patient_id, patient.first_name, patient.last_name, " +
                        "patient.address, patient.city, patient.state, patient.zip, " +
                        "patient.phone_number, patient.email " +
                        "FROM patient " +
                        "JOIN users ON users.user_id = patient.patient_id " +
                        "WHERE users.username = ? ;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                patient = mapRowToPatient(results);
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return patient;
    }

    @Override
    public Patient createPatientInfo(Principal principal, Patient patient) {
        String sql = "INSERT INTO patient(" +
                "patient_id, first_name, last_name, address, city, state, zip, phone_number, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING patient_id;";

        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                userId, patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getCity(),
                patient.getState(), patient.getZip(), patient.getPhoneNumber(), patient.getEmail());
        patient.setPatientId(newId);

        return patient;
    }

    @Override
    public boolean updatePatientInfo(Principal principal, Patient patient) {
        String sql = "UPDATE patient " +
                        "SET first_name=?, last_name=?, address=?, " +
                        "city=?, state=?, zip=?, phone_number=?, email=? " +
                        "WHERE patient_id = ?;";
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        int count = jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getAddress(),
                patient.getCity(), patient.getState(), patient.getZip(), patient.getPhoneNumber(), patient.getEmail(), userId);
        return count == 1;
    }



    private  Patient mapRowToPatient(SqlRowSet rowSet){
        Patient patient = new Patient(
                rowSet.getInt("patient_id") ,
                rowSet.getString("first_name"),
                rowSet.getString("last_name"),
                rowSet.getString("address"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getString("zip"),
                rowSet.getString("phone_number"),
                rowSet.getString("email")
                );
                return patient;
    }
}
