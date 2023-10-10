package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import com.techelevator.model.Office;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOfficeDao implements OfficeDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcOfficeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Office> getAllOfficeDetails() {
        List<Office> offices = new ArrayList<>();
        String sql = "SELECT office_id, office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee\n" +
                "\tFROM office_details;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                offices.add(mapRowToOffice(results));
            }
        } catch (CannotGetJdbcConnectionException ex){
            throw new DaoException("Cannot connect to server or database", ex);
        }
        return offices;
    }

    @Override
    public Office getOfficeById(int officeId) {
        String sql = "SELECT office_id, office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee\n" +
                "\tFROM office_details WHERE office_id = ? ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, officeId);
        if (rowSet.next()) {
            Office office = mapRowToOffice(rowSet);
            office.setEmployees(getEmployeesForOfficeId(office.getOfficeId()));
            return office;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateOfficeInfo(Office office) {
        String sql = "UPDATE office_details\n" +
                "\tSET office_name=?, phone_number=?, open_time=?, close_time=?, address=?, city=?, state=?, zip=?, service_fee=?\n" +
                "\tWHERE office_id = ? ;";
        int count = jdbcTemplate.update(sql, office.getOfficeName(), office.getPhoneNumber(), office.getOpenTime(), office.getCloseTime(),
                        office.getAddress(),office.getCity(),office.getState(),office.getZip(),office.getServiceFee(),office.getOfficeId());
        return count == 1;
    }

    public List<Employee> getEmployeesForOfficeId(int officeId){
        List<Employee> result = new ArrayList<>();
        String sql="SELECT employee.employee_id, employee.first_name, employee.last_name\n" +
                "FROM employee\n" +
                "JOIN employee_office\n" +
                "ON employee_office.employee_id = employee.employee_id\n" +
                "JOIN office_details\n" +
                "ON employee_office.office_id = office_details.office_id\n" +
                "WHERE office_details.office_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, officeId);
        while(rowSet.next()){
            Employee employee = mapRowToEmployee(rowSet);
            result.add(employee);
        }
        return result;
    }

    @Override
    public List<Office> getOfficesByEmployeeId(int employeeId) {
        List<Office> result = new ArrayList<>();
        String sql="SELECT office_details.office_id, \n" +
                        "\t\toffice_details.office_name, \n" +
                        "\t\toffice_details.phone_number, \n" +
                        "\t\toffice_details.open_time, \n" +
                        "\t\toffice_details.close_time, \n" +
                        "\t\toffice_details.address, \n" +
                        "\t\toffice_details.city, \n" +
                        "\t\toffice_details.state, \n" +
                        "\t\toffice_details.zip, \n" +
                        "\t\toffice_details.service_fee\n" +
                        "        FROM office_details\n" +
                        "        JOIN employee_office\n" +
                        "        ON employee_office.office_id = office_details.office_id\n" +
                        "        JOIN employee\n" +
                        "        ON employee_office.employee_id = employee.employee_id\n" +
                        "        WHERE employee.employee_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, employeeId);
        while(rowSet.next()){
            Office office = mapRowToOffice(rowSet);
            result.add(office);
        }
        return result;
    }


    private Office mapRowToOffice(SqlRowSet rowSet) {
        Office office = new Office(
                rowSet.getInt("office_id"),
                rowSet.getString("office_name"),
                rowSet.getString("phone_number"),
                rowSet.getTime("open_time"),
                rowSet.getTime("close_time"),
                rowSet.getString("address"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getString("zip"),
                rowSet.getBigDecimal("service_fee")
        );
          return office;
    }

    private Employee mapRowToEmployee(SqlRowSet rowSet){
        Employee employee = new Employee(
        rowSet.getInt("employee_id"),
        rowSet.getString("first_name"),
        rowSet.getString("last_name"),
        rowSet.getString("email"),
        rowSet.getString("mobile_phone")
        );
        return employee;
    }
}
