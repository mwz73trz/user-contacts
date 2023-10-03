package com.techelevator.dao;
import com.techelevator.model.Employee;
import com.techelevator.model.Office;

import java.util.List;

public interface OfficeDao {
    List<Office> getAllOfficeDetails();

    public Office getOfficeById(int officeId);

    List<Employee> getEmployeesForOfficeId(int officeId);
}
