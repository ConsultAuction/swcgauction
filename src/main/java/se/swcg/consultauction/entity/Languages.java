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
    private String id;

    //Not blank
    private String language;

    public Languages(String id, String language) {
        this(language);
        this.id = id;
    }

    public Languages(String language) {
        this.language = language;
    }

    public Languages() {
    }

    public String getId() {
        return id;
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
        return Objects.equals(id, languages.id) &&
                Objects.equals(language, languages.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, language);
    }

    @Override
    public String toString() {
        return "Languages{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
