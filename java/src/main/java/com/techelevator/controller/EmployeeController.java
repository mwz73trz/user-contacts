package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.JdbcEmployeeDao;
import com.techelevator.model.Employee;
import com.techelevator.model.Office;
import com.techelevator.model.Patient;
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
    @RequestMapping(path= "/employees", method = RequestMethod.GET)
    public List<Employee> employeeList(){
        return employeeDao.getAllEmployees();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee result = employeeDao.getEmployeeById(employeeId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return result;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("employee/user")
    public boolean updateEmployeePersonalInfo(Principal principal, @RequestBody Employee updatedEmployeeInfo){
        return  employeeDao.updateEmployeePersonalInfo(principal, updatedEmployeeInfo);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employee")
    public Employee postEmployee(Principal principal, @RequestBody Employee newEmployee) {
        return employeeDao.createEmployeeInfo(principal, newEmployee);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/employee/officeId")
    public Employee postEmployeeOfficeId(Principal principal, @RequestBody Employee newEmployee) {
       return employeeDao.createNewEmployeeOffice(principal, newEmployee);
    }
}


