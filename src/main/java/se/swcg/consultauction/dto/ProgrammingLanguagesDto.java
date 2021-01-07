package se.swcg.consultauction.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ProgrammingLanguagesDto {

    private String languagesId;

    @NotBlank
    private String language;

    public ProgrammingLanguagesDto(String languagesId, @NotBlank String language) {
        this.languagesId = languagesId;
        this.language = language;
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
        ProgrammingLanguagesDto that = (ProgrammingLanguagesDto) o;
        return Objects.equals(languagesId, that.languagesId) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languagesId, language);
    }

    @Override
    public String toString() {
        return "PrePopLanguagesDto{" +
                "languagesId='" + languagesId + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
