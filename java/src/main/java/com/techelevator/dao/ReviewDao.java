package com.techelevator.dao;

import com.techelevator.model.Review;

import java.security.Principal;
import java.util.List;

public interface ReviewDao {
    List<Review> getAllReviews();

    List<Review> getReviewsByOffice(Principal principal);

    public Review createReview(Review review);

}
