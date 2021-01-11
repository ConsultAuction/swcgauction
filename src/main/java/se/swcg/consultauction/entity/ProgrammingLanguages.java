package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ProgrammingLanguages {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String languageId;

    @Column(nullable = false, unique = true)
    private String language;

    public ProgrammingLanguages(String languageId, String language) {
        this(language);
        this.languageId = languageId;
    }

    public ProgrammingLanguages(String language) {
        this.language = language;
    }

    public ProgrammingLanguages() {
    }

    public String getLanguageId() {
        return languageId;
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
        ProgrammingLanguages that = (ProgrammingLanguages) o;
        return Objects.equals(languageId, that.languageId) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, language);
    }

    @Override
    public String toString() {
        return "PrePopLanguages{" +
                "languagesId='" + languageId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
