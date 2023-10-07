package com.techelevator.controller;

import com.techelevator.dao.ReviewReplyDao;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewReply;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ReviewReplyController {
    private ReviewReplyDao reviewReplyDao;

    public ReviewReplyController(ReviewReplyDao reviewReplyDao) {
        this.reviewReplyDao = reviewReplyDao;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviews/{reviewId}/replies")
    public ReviewReply postReviewReply(Principal principal, @PathVariable int reviewId, @RequestBody ReviewReply newreviewReply){
        return reviewReplyDao.respondReviewByUser(principal, reviewId, newreviewReply);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/reviews/{reviewId}/replies")
    public List<ReviewReply> getRepliesByReviewId(@PathVariable int reviewId) {
        return reviewReplyDao.replyListByReviewId(reviewId);
    }
}
