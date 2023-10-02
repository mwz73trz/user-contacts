package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.JdbcEmployeeDao;
import com.techelevator.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EmployeeController {

    private EmployeeDao employeeDao;

    public EmployeeController(JdbcEmployeeDao employeeDao){
        this.employeeDao= employeeDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/employee", method = RequestMethod.GET)
    public List<Employee> employeeList(){
        return employeeDao.getAllEmployees();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/employee/user", method = RequestMethod.GET)
    public Employee getEmployee(Principal principal){
        Employee result = employeeDao.getEmployeeByUser(principal.getName());
        if(result.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        } else {
            return result;
        }
        }
}
