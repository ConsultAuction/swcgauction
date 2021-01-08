package se.swcg.consultauction.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.swcg.consultauction.entity.ProgrammingLanguages;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProgrammingLanguagesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProgrammingLanguagesRepository repo;

    ProgrammingLanguages languages;

    @BeforeEach
    void setUp() {
        languages = new ProgrammingLanguages("Java");
        entityManager.persistAndFlush(languages);
    }

    @Test
    void findAll() {
        List<ProgrammingLanguages> list = repo.findAll();
        int size = 1;

        assertEquals(size, list.size());
    }

    @Test
    void findById() {
        Optional<ProgrammingLanguages> found = repo.findById(languages.getLanguagesId());

        assertTrue(found.isPresent());
        assertThat(found.get().getLanguagesId().equals(languages.getLanguagesId()));
    }
}