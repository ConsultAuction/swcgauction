package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Languages {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String languagesId;

    private String language;

    public Languages(String languagesId, String language) {
        this(language);
        this.languagesId = languagesId;
    }

    public Languages(String language) {
        this.language = language;
    }

    public Languages() {
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
        Languages languages = (Languages) o;
        return Objects.equals(languagesId, languages.languagesId) &&
                Objects.equals(language, languages.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languagesId, language);
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id='" + languagesId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
