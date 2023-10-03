package com.techelevator.controller;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Employee;
import com.techelevator.model.Office;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
}
