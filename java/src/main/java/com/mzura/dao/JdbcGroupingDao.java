package com.mzura.dao;

import com.mzura.exception.DaoException;
import com.mzura.model.Grouping;
import com.mzura.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupingDao implements GroupingDao{

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcGroupingDao(JdbcTemplate jdbcTemplate, UserDao userDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }
    @Override
    public List<Grouping> getGroupingsByUser(Principal principal) {
        List<Grouping> groupings = new ArrayList<>();
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        String sql = "SELECT grouping_id, title, user_id FROM grouping WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                groupings.add(mapRowToGrouping(results));
            }
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return groupings;
    }

    @Override
    public Grouping getGroupingById(int groupingId) {
        Grouping grouping = null;
        String sql = "SELECT grouping_id, title, user_id FROM grouping WHERE grouping_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupingId);
            if (results.next()) {
                grouping = mapRowToGrouping(results);
            }
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return grouping;
    }

    @Override
    public Grouping createGrouping(Principal principal, Grouping grouping) {
        String sql = "INSERT INTO grouping (title, user_id) " +
                "VALUES (?, ?) RETURNING grouping_id;";
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        try {
            int newGroupingId = jdbcTemplate.queryForObject(sql, int.class, grouping.getTitle(), userId);
            grouping.setGroupingId(newGroupingId);
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server of database", ex);
        }catch (DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation", ex);
        }
        return grouping;
    }

    @Override
    public boolean updateGrouping(int groupingId, Grouping grouping) {
        String sql = "UPDATE grouping SET title = ? WHERE grouping_id = ?;";
        int count = jdbcTemplate.update(sql, grouping.getTitle(), groupingId);
        return count == 1;
    }

    @Override
    public boolean deleteGrouping(int groupingId) {
        String sql = "DELETE FROM grouping WHERE grouping_id = ?;";
        int count = jdbcTemplate.update(sql, groupingId);
        return count == 1;
    }

    private Grouping mapRowToGrouping(SqlRowSet rowSet) {
        return new Grouping(
                rowSet.getInt("grouping_id"),
                rowSet.getString("title"),
                rowSet.getInt("user_id")
        );
    }
}
