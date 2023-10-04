package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        String sql = "SELECT employee_id, user_id, first_name, last_name, office_id\n" +
                        "\tFROM employee\n" +
                        "\tWHERE employee_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);
            if (results.next()) {
                employee = mapRowToEmployee(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return employee;
    }

    @Override
    public void updateEmployeeInfo(Employee employee) {
        String sql = "UPDATE employee " +
                     "JOIN office_details " +
                         "ON employee.office_id = office_details.office_id " +
                     "SET employee.user_id = ?, " +
                        "employee.first_name = ?, " +
                        "employee.last_name = ?, " +
                        "employee.office_id = office_details.office_id " +
                        "WHERE employee.employee_id = ?";

        jdbcTemplate.update(sql,
                employee.getUserId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmployeeId());
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
