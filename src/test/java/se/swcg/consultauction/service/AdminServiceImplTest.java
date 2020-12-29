package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.AdminForm;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminDtoConversionService converter;

    private Admin admin1;
    private Admin admin2;

    @BeforeEach
    void setUp() {
        admin1 = new Admin("firstname", "lastname", "f@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));
        admin1 = adminRepository.save(admin1);

        admin2 = new Admin("firstname2", "lastname2", "f@l.com2",
                "password2", "Admin", true,
                LocalDate.of(2020, 12, 10));
        admin2 = adminRepository.save(admin2);
    }

    //TODO Test for exceptions

    @Test
    void test_findById_should_return_optional_of_findById() {
        String id =  admin1.getAdminId();

        AdminDto result = adminService.findById(id);

        assertEquals(id, result.getAdminId());
    }

    @Test
    void test_findById_should_return_exception() {
        String id = "0";

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> adminService.findById(id));

        assertThat(e).hasMessageContaining(id);
    }

    @Test
    void test_findAll_should_return_list_size_of_2() {
        int size = 2;

        List<AdminDto> result = adminService.findAll();

        assertEquals(size, result.size());
    }

    @Test
    void test_findAll_should_return_exception() {
        adminRepository.delete(admin1);
        adminRepository.delete(admin2);

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, adminService::findAll);

        assertThat(e).hasMessageContaining("Could not find any admins");
    }

    @Test
    void test_findByEmail_should_return_optinal_of_email() {
        String email = admin1.getEmail();

        AdminDto result = adminService.findByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    void test_findByEmail_should_return_exception() {
        String email = "null";

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByEmail(email));

        assertThat(e).hasMessageContaining(email);
    }

    @Test
    void test_findByRole_should_return_list_of_roles() {
        String role = admin1.getRole();
        int size = 2;

        List<AdminDto> result = adminService.findByRole(role);

        assertEquals(size, result.size());
    }

    @Test
    void test_findByRole_should_return_exception() {
        String role = "null";

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByEmail(role));

        assertThat(e).hasMessageContaining(role);
    }

    @Test
    void test_findByActive_should_return_list_of_active() {
        boolean active = true;
        int size = 2;

        List<AdminDto> result = adminService.findByActive(active);

        assertEquals(size, result.size());
    }

    @Test
    void test_findByActive_should_return_exception() {
        boolean active = false;

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByActive(active));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_findByLastActive_should_return_list_of_lastActive() {
        LocalDate date = admin1.getLastActive();
        int size = 1;

        List<AdminDto> result = adminService.findByLastActive(date);

        assertEquals(size, result.size());
    }

    @Test
    void test_findByLastActive_should_return_exception() {
        LocalDate date = LocalDate.of(2019, 7, 20);

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByLastActive(date));

        assertThat(e).hasMessageContaining(date.toString());
    }

    @Test
    void test_create_successfully() {
        int size = adminService.findAll().size() + 1;

        AdminDto toCreate = new AdminDto(null, "firstname3", "lastname3", "f@l.com3",
                "password3", "Admin", true,
                LocalDate.of(2020, 12, 1));

        AdminDto dto = adminService.create(toCreate);

        assertNotNull(dto.getAdminId());
        assertEquals(size, adminService.findAll().size());
    }

    @Test
    void test_update_successfully() {
        String oldName = admin1.getFirstName();
        String newFirstName = "NameFirst";

        AdminForm toUpdate = new AdminForm(admin1.getAdminId(), newFirstName, admin1.getLastName(), admin1.getEmail(),
                admin1.getPassword(), admin1.getRole(), admin1.isActive(), admin1.getLastActive());

        AdminDto updatedAdmin = adminService.update(toUpdate);

        assertEquals(newFirstName, updatedAdmin.getFirstName());
        assertNotEquals(oldName, updatedAdmin.getFirstName());
    }

    @Test
    void test_update_without_id_should_return_exception() {
        AdminForm toUpdate = new AdminForm(null, admin1.getFirstName(), admin1.getLastName(), admin1.getEmail(),
                admin1.getPassword(), admin1.getRole(), admin1.isActive(), admin1.getLastActive());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> adminService.update(toUpdate));

        assertThat(e).hasMessageContaining("Invalid id");
    }

    @Test
    void test_delete_successfully() {
        int size = adminService.findAll().size() - 1;

        adminService.delete(converter.adminToDto(admin1));

        assertEquals(size, adminService.findAll().size());
    }

    @Test
    void test_delete_should_return_exception() {
        AdminDto toDelete = new AdminDto(null, admin1.getFirstName(), admin1.getLastName(), admin1.getEmail(),
                admin1.getPassword(), admin1.getRole(), admin1.isActive(), admin1.getLastActive());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> adminService.delete(toDelete));

        assertThat(e).hasMessageContaining("Invalid id");
    }
}