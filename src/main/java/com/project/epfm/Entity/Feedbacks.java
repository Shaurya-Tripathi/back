package com.project.epfm.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FromEmployeeId", nullable = false)
    private Employee fromEmployee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ToEmployeeId", nullable = false)
    private Employee toEmployee;

    @Column(name = "FeedbackType")
    private String feedbackType;

    @Column(name = "Comment")
    private String comment;


    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Employee getFromEmployee() {
        return fromEmployee;
    }

    public void setFromEmployee(Employee fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    public Employee getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(Employee toEmployee) {
        this.toEmployee = toEmployee;
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
