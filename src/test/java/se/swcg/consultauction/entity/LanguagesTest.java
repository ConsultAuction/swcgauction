package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LanguagesTest {
    Languages languages;
    String languageId;
    String languageName;

    @BeforeEach
    void setUp() {
        languageId = "language1";
        languageName = "Java";
        languages = new Languages(languageId, languageName);
    }

    @Test
    void getId() {
        String id = languages.getLanguagesId();

        assertEquals(id, languageId);
    }

    @Test
    void getLanguage() {
        String name = languages.getLanguage();

        assertEquals(name, languageName);
    }

    @Test
    void setLanguage() {
        String newName = "JavaScript";
        languages.setLanguage(newName);

        assertNotEquals(languages.getLanguage(), languageName);
        assertEquals(newName, languages.getLanguage());
    }

    @Test
    void testEquals() {
        Languages newLanguage = new Languages("language2", "C#");

        assertNotEquals(languages, newLanguage);
    }

    @Test
    void testHashCode() {
        Languages newLanguage = new Languages("language2", "C#");

        int newLanguageHash = newLanguage.hashCode();
        int originalLanguageHash = languages.hashCode();

        assertNotEquals(originalLanguageHash, newLanguageHash);
    }

    @Test
    void testToString() {
        String toString = languages.toString();

        assertTrue(toString.contains(languages.getLanguagesId()));
        assertTrue(toString.contains(languages.getLanguage()));
    }
}