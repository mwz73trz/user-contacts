package com.techelevator.model;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.Date;

public class Review {
    private int reviewId;

    private int userId;

    private Date reviewDate;

    private String review;

    private int officeId;

    public Review() {
    }

    public Review(int reviewId, int userId, Date reviewDate, String review, int officeId) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.reviewDate = reviewDate;
        this.review = review;
        this.officeId = officeId;
    }
//Setters

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    //Getters

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getReview() {
        return review;
    }

    public int getOfficeId() {
        return officeId;
    }
}
