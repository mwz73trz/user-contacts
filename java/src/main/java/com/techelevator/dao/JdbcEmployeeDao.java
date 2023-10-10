package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final UserDao userDao;

    private final OfficeDao officeDao;

    public JdbcEmployeeDao(DataSource dataSource, UserDao userDao, OfficeDao officeDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = userDao;
        this.officeDao = officeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employee_id, first_name, last_name, email, mobile_phone " +
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
        String sql = "SELECT employee.employee_id, employee.first_name, employee.last_name, " +
                        "employee.email, employee.mobile_phone " +
                        "FROM employee " +
                        "JOIN users " +
                        "ON users.user_id = employee.employee_id " +
                        "WHERE users.username = ?; ";
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
        String sql = "SELECT employee_id, first_name, last_name, email, mobile_phone " +
                        "FROM employee " +
                        "WHERE employee_id = ?;";
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
    public Employee createEmployeeInfo(Principal principal, Employee employee) {
        String sql = "INSERT INTO employee(employee_id, first_name, last_name, email, mobile_phone) " +
                        "VALUES (?, ?, ?, ?, ?) " +
                        "RETURNING employee_id;";

        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, userId, employee.getFirstName(),
                employee.getLastName(), employee.getEmail(), employee.getMobilePhone());
        employee.setEmployeeId(newId);

        return employee;
    }

    @Override
     public Employee createNewEmployeeOffice(Principal principal, Employee employee) {
        String sql = "INSERT INTO employee_office (employee_id, office_id) " +
                        "VALUES (?, ?) " +
                        "RETURNING office_id ;";
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        try {
            Integer newOfficeId = jdbcTemplate.queryForObject(sql, Integer.class,  userId, employee.getOfficeId());
            employee.setOfficeId(newOfficeId);
            return employee;
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Cannot connect to database. Try again later");
        }
    }



    private Employee mapRowToEmployee(SqlRowSet rs){
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        employee.setMobilePhone(rs.getString("mobile_phone"));
        return employee;
    }




}
