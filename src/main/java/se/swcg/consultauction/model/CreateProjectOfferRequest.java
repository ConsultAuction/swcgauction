package se.swcg.consultauction.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateProjectOfferRequest {

    @NotBlank(message = "please enter user id.")
    private String consultantId;

    @NotBlank(message = "please enter project id.")
    private String projectId;

    private LocalDate startTime;

    private double bids;

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public double getBids() {
        return bids;
    }

    public void setBids(double bids) {
        this.bids = bids;
    }
}


