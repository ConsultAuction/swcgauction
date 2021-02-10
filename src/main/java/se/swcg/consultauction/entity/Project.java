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

    @NotBlank
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int workLoad;

    @NotBlank
    private String description;

    @NotBlank
    private String located;
    private boolean distanceWork;
    private boolean companyHardware;

    @NotBlank
    private String contactName;

    @Column(unique = true)
    private String contactEmail;

    @NotBlank
    private String contactPhoneNumber;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Client client;

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, int workLoad,
                   String description, String located, boolean distanceWork, boolean companyHardware,
                   String contactName, String contactEmail, String contactPhoneNumber, Client client) {
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
        this.client = client;
    }

    public Project(String projectName, LocalDate startDate, LocalDate endDate, int workLoad,
                   String description, String located, boolean distanceWork, boolean companyHardware,
                   String contactName, String contactEmail, String contactPhoneNumber, Client client) {
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
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                Objects.equals(client, project.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, startDate, endDate, workLoad,
                description, located, distanceWork, companyHardware,
                contactName, contactEmail, contactPhoneNumber, client);
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
                ", client=" + client +
                '}';
    }
}