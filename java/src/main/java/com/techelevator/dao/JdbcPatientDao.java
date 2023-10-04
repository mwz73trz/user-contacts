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

@Component
public class JdbcPatientDao implements PatientDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcPatientDao(DataSource dataSource, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = userDao;
    }

    @Override
    public Patient getPatientByUser(String username) {
        Patient patient = null;
        String sql = "SELECT patient.patient_id, patient.first_name, patient.last_name\n" +
                "\tFROM patient \n" +
                "\tJOIN users ON users.user_id = patient.patient_id\n" +
                "\tWHERE users.username = ? ;";
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
        String sql = "INSERT INTO patient(\n" +
                "\tpatient_id, first_name, last_name)\n" +
                "\tVALUES (?, ?, ?) RETURNING patient_id;";

        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, userId, patient.getFirstName(), patient.getLastName());
        patient.setPatientId(newId);

        return patient;
    }



    private  Patient mapRowToPatient(SqlRowSet rowSet){
        Patient patient = new Patient(
                rowSet.getInt("patient_id") ,
                rowSet.getString("first_name"),
                rowSet.getString("last_name")
                );
                return patient;
    }
}
