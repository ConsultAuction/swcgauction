package se.swcg.consultauction.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Project {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String projectId;
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

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private User user;

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, int workLoad,
                   String description, String located, boolean distanceWork, boolean companyHardware,
                   String contactName, String contactEmail, String contactPhoneNumber, User user) {
        this.projectId = projectId;
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
        this.user = user;
    }

    public Project(String projectName, LocalDate startDate, LocalDate endDate, int workLoad,
                   String description, String located, boolean distanceWork, boolean companyHardware,
                   String contactName, String contactEmail, String contactPhoneNumber, User user) {
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
        this.user = user;
    }

    public Project() {
    }

    public String getProjectId() {
        return projectId;
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

    public User getUser() {
        return user;
    }

    public void setClient(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return workLoad == project.workLoad && distanceWork == project.distanceWork &&
                companyHardware == project.companyHardware &&
                Objects.equals(projectId, project.projectId) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(startDate, project.startDate) &&
                Objects.equals(endDate, project.endDate) &&
                Objects.equals(description, project.description) &&
                Objects.equals(located, project.located) &&
                Objects.equals(contactName, project.contactName) &&
                Objects.equals(contactEmail, project.contactEmail) &&
                Objects.equals(contactPhoneNumber, project.contactPhoneNumber) &&
                Objects.equals(user, project.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, startDate, endDate, workLoad,
                description, located, distanceWork, companyHardware,
                contactName, contactEmail, contactPhoneNumber, user);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", workLoad=" + workLoad +
                ", description='" + description + '\'' +
                ", located='" + located + '\'' +
                ", distanceWork=" + distanceWork +
                ", companyHardware=" + companyHardware +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                ", user=" + user +
                '}';
    }
}
