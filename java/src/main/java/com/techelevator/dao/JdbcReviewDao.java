package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements  ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT review_id, user_id, review_date, review, office_id\n" +
                "\tFROM review;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                reviews.add(mapRowToReview(results));
            }
        } catch (CannotGetJdbcConnectionException ex){
            throw  new DaoException("Cannot connect to server or database", ex);
        }
        return reviews;
    }

    private Review mapRowToReview(SqlRowSet rowSet) {
        Review review = new Review(
                rowSet.getInt("review_id"),
                rowSet.getInt("user_id"),
                rowSet.getDate("review_date"),
                rowSet.getString("review"),
                rowSet.getInt("office_id")
        );
        return review;
    }
}
