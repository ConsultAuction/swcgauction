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
    private String id;
    private boolean frontend;
    private boolean backend;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Certificate> certificate;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Experience> experience;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Languages> language;

    public Qualifications(String id, boolean frontend, boolean backend, List<Certificate> certificate, List<Experience> experience, List<Languages> language) {
        this(frontend, backend, certificate, experience, language);
        this.id = id;
    }

    public Qualifications(boolean frontend, boolean backend, List<Certificate> certificate, List<Experience> experience, List<Languages> language) {
        this.frontend = frontend;
        this.backend = backend;
        this.certificate = certificate;
        this.experience = experience;
        this.language = language;
    }

    public Qualifications() {
    }

    public String getId() {
        return id;
    }

    public boolean isFrontend() {
        return frontend;
    }

    public boolean isBackend() {
        return backend;
    }

    public List<Certificate> getCertificate() {
        return certificate;
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

    public void setCertificate(List<Certificate> certificate) {
        this.certificate = certificate;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public void setLanguage(List<Languages> language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qualifications that = (Qualifications) o;
        return frontend == that.frontend &&
                backend == that.backend &&
                Objects.equals(id, that.id) &&
                Objects.equals(certificate, that.certificate) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, frontend, backend, certificate, experience, language);
    }

    @Override
    public String toString() {
        return "Qualifications{" +
                "id='" + id + '\'' +
                ", frontend=" + frontend +
                ", backend=" + backend +
                ", certificate=" + certificate +
                ", experience=" + experience +
                ", language=" + language +
                '}';
    }
}
