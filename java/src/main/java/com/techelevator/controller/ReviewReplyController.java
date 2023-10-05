package com.techelevator.controller;

import com.techelevator.dao.ReviewReplyDao;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewReply;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ReviewReplyController {
    private ReviewReplyDao reviewReplyDao;

    public ReviewReplyController(ReviewReplyDao reviewReplyDao) {
        this.reviewReplyDao = reviewReplyDao;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviews/review-reply")
    public ReviewReply postReviewReply(@RequestBody ReviewReply newreviewReply){
        return reviewReplyDao.respondReviewByUser(newreviewReply);
    }
}
