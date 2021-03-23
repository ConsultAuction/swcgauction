package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
    private User consultant;

    private boolean accepted;
    private boolean rejected;
    private LocalDateTime startTime;
    private boolean selected;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private Set<Bids> bids;

    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int workLoad;
    private String description;
    private String located;
    private boolean distanceWork;
    private boolean companyHardware;
    private String contactName;
    private String contactEmail;
    private String contactPhoneNumber;
    private String clientId;

    public ProjectOffer(String projectOfferId, User consultant, boolean accepted, boolean rejected, LocalDateTime startTime, boolean selected, Set<Bids> bids, String projectName, LocalDate startDate, LocalDate endDate, int workLoad, String description, String located, boolean distanceWork, boolean companyHardware, String contactName, String contactEmail, String contactPhoneNumber, String clientId) {
        this.projectOfferId = projectOfferId;
        this.consultant = consultant;
        this.accepted = accepted;
        this.rejected = rejected;
        this.startTime = startTime;
        this.selected = selected;
        this.bids = bids;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workLoad = workLoad;
        this.description = description;
        this.located = located;
        this.distanceWork = distanceWork;
        this.companyHardware = companyHardware;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.clientId = clientId;
    }

    public ProjectOffer(User consultant, boolean accepted, boolean rejected, LocalDateTime startTime, boolean selected, Set<Bids> bids, String projectName, LocalDate startDate, LocalDate endDate, int workLoad, String description, String located, boolean distanceWork, boolean companyHardware, String contactName, String contactEmail, String contactPhoneNumber, String clientId) {
        this.consultant = consultant;
        this.accepted = accepted;
        this.rejected = rejected;
        this.startTime = startTime;
        this.selected = selected;
        this.bids = bids;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workLoad = workLoad;
        this.description = description;
        this.located = located;
        this.distanceWork = distanceWork;
        this.companyHardware = companyHardware;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.clientId = clientId;
    }

    public ProjectOffer() {
    }

    public String getProjectOfferId() {
        return projectOfferId;
    }

    public User getConsultant() {
        return consultant;
    }

    public void setConsultant(User consultant) {
        this.consultant = consultant;
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

    public Set<Bids> getBids() {
        return bids;
    }

    public void setBids(Set<Bids> bids) {
        this.bids = bids;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocated() {
        return located;
    }

    public void setLocated(String located) {
        this.located = located;
    }

    public boolean isDistanceWork() {
        return distanceWork;
    }

    public void setDistanceWork(boolean distanceWork) {
        this.distanceWork = distanceWork;
    }

    public boolean isCompanyHardware() {
        return companyHardware;
    }

    public void setCompanyHardware(boolean companyHardware) {
        this.companyHardware = companyHardware;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectOffer that = (ProjectOffer) o;
        return accepted == that.accepted &&
                rejected == that.rejected &&
                selected == that.selected &&
                workLoad == that.workLoad &&
                distanceWork == that.distanceWork &&
                companyHardware == that.companyHardware &&
                Objects.equals(projectOfferId, that.projectOfferId) &&
                Objects.equals(consultant, that.consultant) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(bids, that.bids) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(located, that.located) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(contactEmail, that.contactEmail) &&
                Objects.equals(contactPhoneNumber, that.contactPhoneNumber) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectOfferId, consultant, accepted, rejected, startTime, selected, bids, projectName, startDate, endDate, workLoad, description, located, distanceWork, companyHardware, contactName, contactEmail, contactPhoneNumber, clientId);
    }


}
