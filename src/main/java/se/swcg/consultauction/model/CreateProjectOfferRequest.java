package se.swcg.consultauction.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CreateProjectOfferRequest {

    @NotBlank(message = "please enter user id.")
    private String userId;

    @NotBlank(message = "please enter project id.")
    private String projectId;

    @NotBlank(message = "please enter start time.")
    private LocalDateTime startTime;

    @NotBlank(message = "please enter bid.")
    private double bids;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getBids() {
        return bids;
    }

    public void setBids(double bids) {
        this.bids = bids;
    }
}


