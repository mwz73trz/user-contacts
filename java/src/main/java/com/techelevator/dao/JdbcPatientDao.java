package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Patient;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
@Component
public class JdbcPatientDao implements PatientDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Patient getPatientByUser(String username) {
        Patient patient = null;
        String sql = "SELECT patient.patient_id, patient.first_name, patient.last_name, patient.user_id\n" +
                "\tFROM patient \n" +
                "\tJOIN users ON users.user_id = patient.user_id\n" +
                "\tWHERE users.username = ? ;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if(results.next()){
                patient=mapRowToPatient(results);
            }
        } catch (CannotGetJdbcConnectionException ex){
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return patient;
    }

    private  Patient mapRowToPatient(SqlRowSet rowSet){
        Patient patient = new Patient(
                rowSet.getInt("patient_id") ,
                rowSet.getString("first_name"),
                rowSet.getString("last_name"),
                rowSet.getInt("user_id")
                );
                return patient;
    }
}
