package com.techelevator.dao;


import com.techelevator.model.Employee;
import com.techelevator.model.Office;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.List;


public interface EmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeByUser(String username);
//    void updateEmployeeInfo(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee createEmployeeInfo(Principal principal, Employee employee);
    Employee  createNewEmployeeOffice(Principal principal, Employee employee);


}
