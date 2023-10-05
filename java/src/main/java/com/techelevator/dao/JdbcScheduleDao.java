package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Schedule;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcScheduleDao implements ScheduleDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Schedule getScheduleByEmployeeId(int employeeId) {
        Schedule schedule = null;
        String sql = "SELECT schedule_id, employee_id, start_time, end_time\n" +
                        "\tFROM employee_schedule\n" +
                        "\tWHERE employee_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);
            if (results.next()) {
                schedule = mapRowToSchedule(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return schedule;
    }

    private Schedule mapRowToSchedule(SqlRowSet rowSet) {
        Schedule schedule = new Schedule(
                rowSet.getInt("schedule_id"),
                rowSet.getInt("employee_id"),
                rowSet.getTime("start_time"),
                rowSet.getTime("end_time")
        );
        return schedule;
    }
}
