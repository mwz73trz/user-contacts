package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

//    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
//       this.jdbcTemplate = jdbcTemplate;
//    }
    public JdbcEmployeeDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employee_id, user_id, first_name, last_name, office_id " +
                     "FROM employee;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Employee employee = mapRowToEmployee(results);
                employees.add(employee);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
            return employees;
    }

    @Override
    public Employee getEmployeeByUser(String username) {
        Employee employee = null;
        String sql = "SELECT employee.employee_id, employee.user_id,employee.first_name, employee.last_name, employee.office_id " +
                      "FROM employee " +
                        "JOIN users " +
                             "ON users.user_id = employee.user_id " +
                      "WHERE users.username = ?; " ;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                employee = mapRowToEmployee(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return employee;
    }

    private Employee mapRowToEmployee(SqlRowSet rs){
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setUserId(rs.getInt("user_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setOfficeId(rs.getInt("office_id"));
        return employee;
    }




}
