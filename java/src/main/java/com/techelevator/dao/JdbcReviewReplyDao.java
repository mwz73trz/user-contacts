package com.techelevator.dao;

import com.techelevator.model.ReviewReply;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;

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
}
