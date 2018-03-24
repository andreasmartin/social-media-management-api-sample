package ch.andreasmartin.smm.business.dto;

/**
 * Created by andreas.martin on 05.06.2017.
 */
public class ReviewRequest {
    private boolean approved;
    private String review;
    private String reviewerName;

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
