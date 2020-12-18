package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Experience {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String experienceId;
    private String experienceContent;

    public Experience(String experienceId, String experienceContent) {
        this(experienceContent);
        this.experienceId = experienceId;
    }

    public Experience(String experienceContent) {
        this.experienceContent = experienceContent;
    }

    public Experience() {
    }

    public String getExperienceId() {
        return experienceId;
    }

    public String getExperienceContent() {
        return experienceContent;
    }

    public void setExperienceContent(String experienceContent) {
        this.experienceContent = experienceContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(experienceId, that.experienceId) &&
                Objects.equals(experienceContent, that.experienceContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceId, experienceContent);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id='" + experienceId + '\'' +
                ", experienceContent='" + experienceContent + '\'' +
                '}';
    }
}
