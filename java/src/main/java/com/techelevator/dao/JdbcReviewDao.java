package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements  ReviewDao{

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
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

    @Override
    public List<Review> getReviewsByOffice(Principal principal) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT review_id, user_id, review_date, review, office_id " +
                    " FROM review " +
                    " WHERE user_id = ? ; " ;

        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                reviews.add(mapRowToReview(results));
            }
        } catch (CannotGetJdbcConnectionException ex){
            throw  new DaoException("Cannot connect to server or database", ex);
        }
        return reviews;
    }

    @Override
    public Review createReview(Review review) {
        String sql ="INSERT INTO review(\n" +
                "\t user_id, review, office_id)\n" +
                "\tVALUES ( ?, ?, ?) RETURNING review_id;";
        int newReviewId = jdbcTemplate.queryForObject(sql,Integer.class,review.getUserId(),review.getReview(), review.getOfficeId());
        review.setReviewId(newReviewId);
        return review;
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
