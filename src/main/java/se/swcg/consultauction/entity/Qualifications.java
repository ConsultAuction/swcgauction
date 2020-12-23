package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Qualifications {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String qualificationsId;
    private boolean frontend;
    private boolean backend;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Experience> experience;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Languages> language;

    public Qualifications(String qualificationsId, boolean frontend, boolean backend, List<Experience> experience, List<Languages> language) {
        this(frontend, backend, experience, language);
        this.qualificationsId = qualificationsId;
    }

    public Qualifications(boolean frontend, boolean backend, List<Experience> experience, List<Languages> language) {
        this.frontend = frontend;
        this.backend = backend;
        this.experience = experience;
        this.language = language;
    }

    public Qualifications() {
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

    public boolean isBackend() {
        return backend;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public List<Languages> getLanguage() {
        return language;
    }

    public void setFrontend(boolean frontend) {
        this.frontend = frontend;
    }

    public void setBackend(boolean backend) {
        this.backend = backend;
    }

    /*public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public void setLanguage(List<Languages> language) {
        this.language = language;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qualifications that = (Qualifications) o;
        return frontend == that.frontend &&
                backend == that.backend &&
                Objects.equals(qualificationsId, that.qualificationsId) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualificationsId, frontend, backend, experience, language);
    }

    @Override
    public String toString() {
        return "Qualifications{" +
                "qualificationsId='" + qualificationsId + '\'' +
                ", frontend=" + frontend +
                ", backend=" + backend +
                ", experience=" + experience +
                ", language=" + language +
                '}';
    }
}
