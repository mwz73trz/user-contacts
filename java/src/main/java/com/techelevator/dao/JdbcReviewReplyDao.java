package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewReply;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewReplyDao implements ReviewReplyDao{
    private final JdbcTemplate jdbcTemplate;

    private final UserDao userDao;

    public JdbcReviewReplyDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }


    @Override
    public ReviewReply respondReviewByUser(Principal principal, int reviewId, ReviewReply reviewReply) {
        String sql = "INSERT INTO review_reply (review_id, " +
                        "user_id, " +
                        "review_date, " +
                        "review_reply) " +
                        "VALUES (?, ?, ?, ?) " +
                        "RETURNING review_reply_id;";
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        int newReviewReplyId = jdbcTemplate.queryForObject(sql,Integer.class, reviewReply.getReviewId(), userId,
                reviewReply.getReviewDate(), reviewReply.getReviewReply());
        reviewReply.setReviewReplyId(newReviewReplyId);
        return reviewReply;
    }

    @Override
    public List<ReviewReply> replyListByReviewId(int reviewId) {
        List<ReviewReply> replies = new ArrayList<>();
        String sql = "SELECT review_reply_id, review_id, user_id, review_date, review_reply\n" +
                        "\tFROM review_reply\n" +
                        "\tWHERE review_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reviewId);
            while (results.next()) {
                replies.add(mapRowToReviewReply(results));
            }
        } catch (CannotGetJdbcConnectionException ex){
            throw  new DaoException("Cannot connect to server or database", ex);
        }
        return replies;
    }

    private ReviewReply mapRowToReviewReply(SqlRowSet rowSet) {
        ReviewReply reviewReply = new ReviewReply(
                rowSet.getInt("review_reply_id"),
                rowSet.getInt("review_id"),
                rowSet.getInt("user_id"),
                rowSet.getDate("review_date"),
                rowSet.getString("review_reply")
        );
        return reviewReply;
    }
}
