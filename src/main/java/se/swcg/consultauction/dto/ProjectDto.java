package se.swcg.consultauction.dto;
import se.swcg.consultauction.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class ProjectDto {

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

    @NotBlank(message = "Please enter email")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="Not a valid email address")
    private String contactEmail;
    private String contactPhoneNumber;
    private User user;


    public ProjectDto(String projectId, String projectName, LocalDate startDate, LocalDate endDate, int workLoad,
                      String description, String located, boolean distanceWork, boolean companyHardware,
                      String contactName, String contactEmail, String contactPhoneNumber, User client) {
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
        this.user = client;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public void setUser(User user) {
        this.user = user;
    }
}
