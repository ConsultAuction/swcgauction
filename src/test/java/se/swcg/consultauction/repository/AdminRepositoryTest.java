package se.swcg.consultauction.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class AdminRepositoryTest {

    @Autowired
    private AdminRepository repo;
    private Admin testAdmin;

    @BeforeEach
    void setUp() {
        testAdmin = new Admin("firstname", "lastname", "a@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

        testAdmin = repo.save(testAdmin);
    }

    @Test
    void given_findById_should_return_optional_of_adminId() {
        String id = testAdmin.getAdminId();

        Optional<Admin> result = repo.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getAdminId());
    }

    @Test
    void given_findAll_should_return_list_size_of_1() {
        int expectedSize = 1;

        List<Admin> result = repo.findAll();

        assertEquals(expectedSize, result.size());
    }

    @Test
    void given_findAll_should_return_list_size_of_2() {
        repo.save(new Admin("firstname2", "lastname2", "a@l.com2",
                "password2", "Admin2", true,
                LocalDate.of(2020, 12, 23)));

        int expectedSize = 2;

        List<Admin> result = repo.findAll();

        assertEquals(expectedSize, result.size());
    }

    @Test
    void given_findByEmail_should_return_optional_of_adminEmail() {
        String email = testAdmin.getEmail();

        Optional<Admin> result = repo.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

    }

    @Test
    void given_findByRole_should_return_list_size_of_1() {
        int size = 1;

        String role = testAdmin.getRole();
        List<Admin> result = repo.findByRole(role);

        assertEquals(size, result.size());
    }

    @Test
    void given_findByActive_should_return_list_size_of_1() {
        int size = 1;

        boolean active = testAdmin.isActive();
        List<Admin> result = repo.findByActive(active);

        assertEquals(size, result.size());
    }

    @Test
    void given_findByLastActive_should_return_list_size_of_1() {
        int size = 1;

        LocalDate lastActive = testAdmin.getLastActive();
        List<Admin> result = repo.findByLastActive(lastActive);

        assertEquals(size, result.size());
    }


}