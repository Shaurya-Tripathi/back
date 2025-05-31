package com.project.epfm.DTO;

public class FeedbackDTO {
    private long feedbackId;
    private long fromEmployeeId;
    private long toEmployeeId;
    private String feedbackType;
    private String comment;

    // Getters and Setters
    public long getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }
    public long getFromEmployeeId() {
        return fromEmployeeId;
    }
    public void setFromEmployeeId(long fromEmployeeId) {
        this.fromEmployeeId = fromEmployeeId;
    }
    public long getToEmployeeId() {
        return toEmployeeId;
    }
    public void setToEmployeeId(long toEmployeeId) {
        this.toEmployeeId = toEmployeeId;
    }
    public String getFeedbackType() {
        return feedbackType;
    }
    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
