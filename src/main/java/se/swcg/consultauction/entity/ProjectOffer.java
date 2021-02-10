package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ProjectOffer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String projectOfferId;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Project project;

    private boolean accepted;

    private boolean rejected;

    private LocalDateTime startTime;

    private boolean selected;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private Bids bids;

    public ProjectOffer(String projectOfferId, User user, Project project, boolean accepted,
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

    public ProjectOffer(User user, Project project, boolean accepted,
                        boolean rejected, LocalDateTime startTime, boolean selected, Bids bids) {
        this.user = user;
        this.project = project;
        this.accepted = accepted;
        this.rejected = rejected;
        this.startTime = startTime;
        this.selected = selected;
        this.bids = bids;
    }

    public ProjectOffer() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectOffer that = (ProjectOffer) o;
        return accepted == that.accepted && rejected == that.rejected && selected == that.selected && Objects.equals(projectOfferId, that.projectOfferId) && Objects.equals(user, that.user) && Objects.equals(project, that.project) && Objects.equals(startTime, that.startTime) && Objects.equals(bids, that.bids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectOfferId, user, project, accepted, rejected, startTime, selected, bids);
    }

    @Override
    public String toString() {
        return "ProjectOffer{" +
                "projectOfferId='" + projectOfferId + '\'' +
                ", user=" + user +
                ", project=" + project +
                ", accepted=" + accepted +
                ", rejected=" + rejected +
                ", startTime=" + startTime +
                ", selected=" + selected +
                ", bids=" + bids +
                '}';
    }
}
