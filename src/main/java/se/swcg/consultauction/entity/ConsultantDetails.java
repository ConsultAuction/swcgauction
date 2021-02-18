package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ConsultantDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String qualificationsId;

    private boolean frontend;
    private boolean backend;
    private boolean availableForHire;
    private int minPrice;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Experience> experience;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Languages> language;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Skills> skills;

    public ConsultantDetails(String qualificationsId, boolean frontend, boolean backend, boolean availableForHire,
                             int minPrice, User user, List<Experience> experience, List<Languages> language,
                             List<Skills> skills) {
        this.qualificationsId = qualificationsId;
        this.frontend = frontend;
        this.backend = backend;
        this.availableForHire = availableForHire;
        this.minPrice = minPrice;
        this.user = user;
        this.experience = experience;
        this.language = language;
        this.skills = skills;
    }

    public ConsultantDetails(boolean frontend, boolean backend, boolean availableForHire,
                             int minPrice, User user, List<Experience> experience, List<Languages> language,
                             List<Skills> skills) {
        this.frontend = frontend;
        this.backend = backend;
        this.availableForHire = availableForHire;
        this.minPrice = minPrice;
        this.user = user;
        this.experience = experience;
        this.language = language;
        this.skills = skills;
    }

    public ConsultantDetails() {
    }

    // TODO add validation for all add/remove methods
    public void addExperience(Experience experienceToAdd) {
        experience.add(experienceToAdd);
    }

    public void removeExperience(Experience experienceToRemove) {
        experience.remove(experienceToRemove);
    }

    public void addLanguage(Languages languageToAdd) {
        language.add(languageToAdd);
    }

    public void removeLanguage(Languages languageToRemove) {
        language.remove(languageToRemove);
    }

    public String getQualificationsId() {
        return qualificationsId;
    }

    public boolean isFrontend() {
        return frontend;
    }

    public void setFrontend(boolean frontend) {
        this.frontend = frontend;
    }

    public boolean isBackend() {
        return backend;
    }

    public void setBackend(boolean backend) {
        this.backend = backend;
    }

    public boolean isAvailableForHire() {
        return availableForHire;
    }

    public void setAvailableForHire(boolean availableForHire) {
        this.availableForHire = availableForHire;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Languages> getLanguage() {
        return language;
    }

    public void setLanguage(List<Languages> language) {
        this.language = language;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantDetails that = (ConsultantDetails) o;
        return frontend == that.frontend &&
                backend == that.backend &&
                availableForHire == that.availableForHire &&
                minPrice == that.minPrice &&
                Objects.equals(qualificationsId, that.qualificationsId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualificationsId, frontend, backend, availableForHire, minPrice, user, experience, language);
    }

    @Override
    public String toString() {
        return "ConsultantDetails{" +
                "qualificationsId='" + qualificationsId + '\'' +
                ", frontend=" + frontend +
                ", backend=" + backend +
                ", availableForHire=" + availableForHire +
                ", minPrice=" + minPrice +
                ", user=" + user +
                ", experience=" + experience +
                ", language=" + language +
                '}';
    }
}
