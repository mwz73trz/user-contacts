package com.techelevator.model;

import java.util.Date;

public class ReviewReply {
    private  int reviewReplyId;

    private int reviewId;

    private int userId;

    private Date reviewDate;

    private String reviewReply;

    public ReviewReply() {
    }

    public ReviewReply(int reviewReplyId, int reviewId, int userId, Date reviewDate, String reviewReply) {
        this.reviewReplyId = reviewReplyId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.reviewDate = reviewDate;
        this.reviewReply = reviewReply;
    }

    //setters
    public void setReviewReplyId(int reviewReplyId) {
        this.reviewReplyId = reviewReplyId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setReviewReply(String reviewReply) {
        this.reviewReply = reviewReply;
    }

    //Getters

    public int getReviewReplyId() {
        return reviewReplyId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getReviewReply() {
        return reviewReply;
    }
}
