package com.techelevator.dao;

import com.techelevator.model.ReviewReply;

import java.security.Principal;
import java.util.List;

public interface ReviewReplyDao {

    ReviewReply respondReviewByUser(Principal principal, int reviewId, ReviewReply reviewReply);
    List<ReviewReply> replyListByReviewId(int reviewId);
}
