package se.swcg.consultauction.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ProgrammingLanguagesDto {

    private String languageId;

    @NotBlank(message = "You need to enter a language")
    @Size(min = 2, max = 30)
    private String language;

    public ProgrammingLanguagesDto(String languageId, String language) {
        this.languageId = languageId;
        this.language = language;
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
        ProgrammingLanguagesDto that = (ProgrammingLanguagesDto) o;
        return Objects.equals(languageId, that.languageId) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, language);
    }

    @Override
    public String toString() {
        return "PrePopLanguagesDto{" +
                "languagesId='" + languageId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
