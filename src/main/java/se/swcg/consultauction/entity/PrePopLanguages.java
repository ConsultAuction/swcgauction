package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PrePopLanguages {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String languagesId;

    @Column(nullable = false, unique = true)
    private String language;

    public PrePopLanguages(String languagesId, String language) {
        this(language);
        this.languagesId = languagesId;
    }

    public PrePopLanguages(String language) {
        this.language = language;
    }

    public PrePopLanguages() {
    }

    public String getLanguagesId() {
        return languagesId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrePopLanguages that = (PrePopLanguages) o;
        return Objects.equals(languagesId, that.languagesId) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languagesId, language);
    }

    @Override
    public String toString() {
        return "PrePopLanguages{" +
                "languagesId='" + languagesId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
