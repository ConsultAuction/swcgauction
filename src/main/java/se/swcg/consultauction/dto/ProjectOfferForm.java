package se.swcg.consultauction.dto;

import se.swcg.consultauction.entity.Bids;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.entity.User;

import java.time.LocalDateTime;

public class ProjectOfferForm {

    private String projectOfferId;

    private User user;

    private Project project;

    private boolean accepted;

    private boolean rejected;

    private LocalDateTime startTime;

    private boolean selected;

    private Bids bids;


    public ProjectOfferForm(String projectOfferId, User user, Project project, boolean accepted,
                           boolean rejected, LocalDateTime startTime, boolean selected, Bids bids) {
        this.projectOfferId = projectOfferId;
        this.user = user;
        this.project = project;
        this.accepted = accepted;
        this.rejected = rejected;
        this.startTime = startTime;
        this.selected = selected;
        this.bids = bids;
    }

    public ProjectOfferForm(User user, Project project, boolean accepted,
                            boolean rejected, LocalDateTime startTime, boolean selected, Bids bids) {
        this.user = user;
        this.project = project;
        this.accepted = accepted;
        this.rejected = rejected;
        this.startTime = startTime;
        this.selected = selected;
        this.bids = bids;
    }

    public String getProjectOfferId() {
        return projectOfferId;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Bids getBids() {
        return bids;
    }

    public void setBids(Bids bids) {
        this.bids = bids;
    }
}
