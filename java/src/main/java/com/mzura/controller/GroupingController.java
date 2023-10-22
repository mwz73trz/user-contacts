package com.mzura.controller;

import com.mzura.dao.GroupingDao;
import com.mzura.dao.JdbcGroupingDao;
import com.mzura.model.Grouping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GroupingController {

    private final GroupingDao groupingDao;

    @Autowired
    public GroupingController(GroupingDao groupingDao) {
        this.groupingDao = groupingDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/groupings", method = RequestMethod.GET)
    public List<Grouping> getGroupings(Principal principal) {
        List<Grouping> groups = groupingDao.getGroupingsByUser(principal);
        if (groups == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Groups found");
        }
        return groups;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groupings/{groupingId}")
    public Grouping getGroupingById(@PathVariable int groupingId) {
        Grouping result = groupingDao.getGroupingById(groupingId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grouping not found");
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/groupings")
    public Grouping createGrouping(Principal principal, @RequestBody Grouping newGrouping) {
        return groupingDao.createGrouping(principal, newGrouping);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/groupings/{groupingId}")
    public Grouping updateGrouping(@PathVariable int groupingId, @RequestBody Grouping updatedGrouping) {
        updatedGrouping.setGroupingId(groupingId);
        if (groupingDao.updateGrouping(groupingId, updatedGrouping)) {
            return updatedGrouping;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found to update");
        }
    }

    @DeleteMapping("/groupings/{groupingId}")
    public void deleteGrouping(@PathVariable int groupingId) {
        if (!groupingDao.deleteGrouping(groupingId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found to delete");
        }
    }
}
