package ch.andreasmartin.smm.business.dto;

import ch.andreasmartin.smm.data.domain.Tweet;

/**
 * Created by andreas.martin on 05.06.2017.
 */
public class ReviewResponse {
    private Long reviewerId;
    private Long tweetId;
    private boolean approved;
    private String review;
    private String reviewerName;

    public ReviewResponse() {}

    public ReviewResponse(Tweet tweet){
        this.tweetId = tweet.getId();
        this.approved = tweet.isApproved();
        this.review = tweet.getReview();
        this.reviewerName = tweet.getReviewer().getPersonName();
        this.reviewerId = tweet.getReviewer().getId();
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
}
