package com.techelevator.controller;

import com.techelevator.dao.OfficeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Appointment;
import com.techelevator.model.Employee;
import com.techelevator.model.Office;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class OfficeController {
    private OfficeDao officeDao;

    public OfficeController(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="offices",method = RequestMethod.GET)
    public List<Office> getOffices(){
        return officeDao.getAllOfficeDetails();
    }

    @GetMapping("offices/{officeId}")
    public Office getOfficeById(@PathVariable int officeId){
        Office result = officeDao.getOfficeById(officeId);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No office found");
        } else{
            return result;
        }
    }

    @PutMapping("offices/{officeId}")
    public Office putOffice(@PathVariable int officeId, @RequestBody Office updatedOffice){
        updatedOffice.setOfficeId(officeId);
        if(officeDao.updateOfficeInfo(updatedOffice)){
            return updatedOffice;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found to update");
        }
    }

    @GetMapping("employee-offices/{employeeId}")
    public List<Office> getOfficesByEmployeeId(@PathVariable int employeeId){
        List<Office> offices = officeDao.getOfficesByEmployeeId(employeeId);
        if(offices.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offices found");
        } else{
            return offices;
        }
    }
}