package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminServiceImplTest {

    @TestConfiguration
    static class AdminServiceImplTestContextConfiguration {

        @Bean
        public AdminService adminService() {
            return new AdminServiceImpl();
        }
    }

    @Autowired
    private AdminService adminService;

    @MockBean
    private AdminRepository repo;

    private Admin admin;
    private AdminDto adminDto;
    private List<Admin> adminList;
    private List<Admin> emptyList;

    @BeforeEach
    void setUp() {
        admin = new Admin("0","firstname", "lastname", "f@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

        adminDto = new AdminDto("1","firstname2", "lastname2", "f@l.com2",
            "password2", "Admin", true,
            LocalDate.of(2020, 12, 23));

        adminList = new ArrayList<>();
        adminList.add(admin);

        emptyList = new ArrayList<>();
    }

    @Test
    void test_findById_should_return_optional_of_findById() {
        when(repo.findById(anyString())).thenReturn(Optional.of(admin));

        AdminDto found = adminService.findById(admin.getAdminId());

        String expectedFirstName = "firstname";
        String actualFirstName = found.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    void test_findById_should_return_exception() {
        when(repo.findById(anyString())).thenReturn(Optional.empty());

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.findById(admin.getAdminId()));

        String expectedMessageContains = "not find";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessageContains));
    }

    @Test
    void test_findAll_should_return_list_size_of_2() {
        when(repo.findAll()).thenReturn(adminList);
        int size = adminList.size();

        List<AdminDto> result = adminService.findAll();

        assertEquals(size, result.size());
    }

    @Test
    void test_findAll_should_return_exception() {
        when(repo.findAll()).thenReturn(emptyList);

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, adminService::findAll);

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_findByEmail_should_return_optional_of_email() {
        when(repo.findByEmail(anyString())).thenReturn(Optional.of(admin));

        AdminDto found = adminService.findByEmail(admin.getEmail());

        assertThat(found.getEmail().contains(admin.getEmail()));
    }

    @Test
    void test_findByEmail_should_return_exception() {
        when(repo.findByEmail(anyString())).thenReturn(Optional.empty());

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByEmail(admin.getEmail()));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_findByRole_should_return_list_of_roles() {
        when(repo.findByRole(anyString())).thenReturn(adminList);
        int size = adminList.size();

        List<AdminDto> result = adminService.findByRole(admin.getRole());

        assertEquals(size, result.size());
        assertTrue(result.get(0).getRole().contains(admin.getRole()));
    }

    @Test
    void test_findByRole_should_return_exception() {
        when(repo.findByRole(anyString())).thenReturn(emptyList);

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByEmail(admin.getRole()));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_findByActive_should_return_list_of_active() {
        when(repo.findByActive(anyBoolean())).thenReturn(adminList);
        int size = adminList.size();

        List<AdminDto> result = adminService.findByActive(admin.isActive());

        assertEquals(size, result.size());
        assertTrue(result.get(0).isActive());
    }

    @Test
    void test_findByActive_should_return_exception() {
        when(repo.findByActive(anyBoolean())).thenReturn(emptyList);

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByActive(admin.isActive()));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_findByLastActive_should_return_list_of_lastActive() {
        when(repo.findByLastActive(any(LocalDate.class))).thenReturn(adminList);
        int size = adminList.size();

        List<AdminDto> result = adminService.findByLastActive(admin.getLastActive());

        assertEquals(size, result.size());
        assertEquals(result.get(0).getLastActive(), admin.getLastActive());
    }

    @Test
    void test_findByLastActive_should_return_exception() {
        when(repo.findByLastActive(any(LocalDate.class))).thenReturn(emptyList);

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.findByLastActive(admin.getLastActive()));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_create_successfully() {
        when(repo.save(any(Admin.class))).thenReturn(admin);

        AdminDto created = adminService.create(adminDto);

        assertThat(created.getFirstName().equals(adminDto.getFirstName()));
    }

    @Test
    void test_update_successfully() {
        when(repo.save(any(Admin.class))).thenReturn(admin);
        when(repo.findById(anyString())).thenReturn(Optional.of(admin));

        AdminDto found = adminService.update(adminDto);

        assertThat(found.getFirstName().contains(adminDto.getFirstName()));
    }

    @Test
    void test_update_with_invalid_object_should_throw_exception() {
        when(repo.save(any(Admin.class))).thenReturn(admin);

        Exception e = assertThrows(ResourceNotFoundException.class, () -> adminService.update(adminDto));

        assertThat(e).hasMessageContaining("Could not find");
    }

    @Test
    void test_update_without_id_should_return_exception() {
        AdminDto toUpdate = new AdminDto(null, admin.getFirstName(), admin.getLastName(), admin.getEmail(),
                admin.getPassword(), admin.getRole(), admin.isActive(), admin.getLastActive());

        Exception e = assertThrows(IllegalArgumentException.class, () -> adminService.update(toUpdate));

        assertThat(e).hasMessageContaining("Invalid id");
    }

    @Test
    void test_delete_successfully() {
        when(repo.findById(anyString()))
                .thenReturn(Optional.of(admin))
                .thenReturn(Optional.empty());

        boolean result = adminService.delete(admin.getAdminId());

        assertTrue(result);
    }

    @Test
    void test_delete_should_return_exception() {
        when(repo.findById(anyString())).thenReturn(Optional.of(admin));

        boolean result = adminService.delete(adminDto.getAdminId());

        assertFalse(result);
    }
}