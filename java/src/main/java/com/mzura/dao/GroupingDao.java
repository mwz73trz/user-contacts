package com.mzura.dao;

import com.mzura.model.Grouping;

import java.security.Principal;
import java.util.List;

public interface GroupingDao {

    public List<Grouping> getGroupingsByUser(Principal principal);
    public Grouping getGroupingById(int groupingId);
    public Grouping createGrouping(Principal principal, Grouping grouping);
    public boolean updateGrouping(int groupingId, Grouping grouping);
    public boolean deleteGrouping(int groupingId);
}
