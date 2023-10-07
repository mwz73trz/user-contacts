package com.techelevator.dao;

import com.techelevator.model.ReviewReply;

import java.security.Principal;

public interface ReviewReplyDao {

    ReviewReply respondReviewByUser(Principal principal, int reviewId, ReviewReply reviewReply);
}
