package com.techelevator.dao;

import com.techelevator.model.ReviewReply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcReviewReplyDao implements ReviewReplyDao{
    private final JdbcTemplate jdbcTemplate;

    private final UserDao userDao;

    public JdbcReviewReplyDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }


    @Override
    public ReviewReply respondReviewByUser(ReviewReply reviewReply) {
        String sql = "INSERT INTO review_reply(\n" +
                "\treview_id, user_id, review_reply)\n" +
                "\tVALUES (  ?, ?, ?) RETURNING review_reply_id;";
        int newReviewReplyId = jdbcTemplate.queryForObject(sql,Integer.class, reviewReply.getReviewId(),reviewReply.getUserId(),
                reviewReply.getReviewReply());
        reviewReply.setReviewReplyId(newReviewReplyId);
        return reviewReply;
    }
}
