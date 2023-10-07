package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Office;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ReviewController {
    private ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="reviews",method = RequestMethod.GET)
    public List<Review> getReviews(){
        return reviewDao.getAllReviews();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="officeReview",method = RequestMethod.GET)
    public List<Review> getReviewsByOffice(Principal principal){
       List<Review> reviews = reviewDao.getReviewsByOffice(principal);
        if (reviews.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reviews found.");
        } else {
            return reviews;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviews")
    public Review postreview(@RequestBody Review newReview){
        return reviewDao.createReview(newReview);
    }

}
