package se.swcg.consultauction.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.swcg.consultauction.entity.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdminRepository repo;

    Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("firstname", "lastname", "a@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

        admin = entityManager.persistAndFlush(admin);
    }

    @Test
    void given_findAll_should_return_list_size_of_1() {

        List<Admin> foundList = repo.findAll();
        int expectedSize = 1;

        assertThat(foundList.contains(admin));
        assertEquals(expectedSize, foundList.size());
    }

    @Test
    void given_findByEmail_should_return_optional_of_adminEmail() {

        Optional<Admin> found = repo.findByEmail(admin.getEmail());

        assertTrue(found.isPresent());
        assertThat(found.get().getEmail().equals(admin.getEmail()));
    }

    @Test
    void given_findByRole_should_return_list_size_of_1() {
        List<Admin> foundList = repo.findByRole(admin.getRole());
        int expectedSize = 1;

        assertThat(foundList.contains(admin));
        assertEquals(expectedSize, foundList.size());
    }

    @Test
    void given_findByActive_should_return_list_size_of_1() {
        List<Admin> foundList = repo.findByActive(admin.isActive());
        int expectedSize = 1;

        assertThat(foundList.contains(admin));
        assertEquals(expectedSize, foundList.size());
    }

    @Test
    void given_findByLastActive_should_return_list_size_of_1() {
        List<Admin> foundList = repo.findByLastActive(admin.getLastActive());
        int expectedSize = 1;

        assertThat(foundList.contains(admin));
        assertEquals(expectedSize, foundList.size());
    }


}