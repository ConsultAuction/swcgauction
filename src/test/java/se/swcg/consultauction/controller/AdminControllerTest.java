package se.swcg.consultauction.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.AdminServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdminServiceImpl service;

    @Autowired
    private JacksonTester<AdminDto> jsonAdminDto;

    private AdminDto adminDto;

    @BeforeEach
    void setUp() {
        adminDto = new AdminDto("0", "firstname", "lastname", "f@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));
    }

    @Test
    void givenFindAll_whenGetFindByAll_thenReturnJsonArray() throws Exception {
        List<AdminDto> allAdmins = Arrays.asList(adminDto);

        given(service.findAll()).willReturn(allAdmins);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(adminDto.getFirstName())));
    }

    @Test
    void givenFindById_whenGetFindById_thenReturnJson() throws Exception {
        given(service.findById(adminDto.getAdminId())).willReturn(adminDto);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/" + adminDto.getAdminId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("adminId", is(adminDto.getAdminId())));
    }

    @Test
    void givenFindById_whenGetFindByIdDoNotExists_thenReturnJsonNotFound() throws Exception {
        given(service.findById("2")).willThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/" + "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void givenFindByEmail_whenGetFindByEmail_thenReturnJson() throws Exception {
        given(service.findByEmail(adminDto.getEmail())).willReturn(adminDto);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/api/admin/email/" + adminDto.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAdminDto.write(adminDto).getJson()
        );
    }

    @Test
    void givenFindByEmail_whenGetFindByEmailDoNotExist_thenReturnJsonNotFound() throws Exception {
        given(service.findByEmail("email")).willThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/email/" + "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void givenFindByRole_whenGetFindByRole_thenReturnJson() throws Exception {
        List<AdminDto> allRoles = Arrays.asList(adminDto);

        given(service.findByRole(adminDto.getRole())).willReturn(allRoles);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/role/" + adminDto.getRole())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].role", is(adminDto.getRole())));
    }

    @Test
    void givenFindByRole_whenGetFindByRoleDoNotExist_thenReturnJsonNotFound() throws Exception {
        given(service.findByRole("null")).willThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/role/" + "null")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void givenFindByActive_whenGetFinByActive_thenReturnJson() throws Exception {
        List<AdminDto> allRoles = Arrays.asList(adminDto);

        given(service.findByActive(adminDto.isActive())).willReturn(allRoles);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/active/" + adminDto.isActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].active", is(adminDto.isActive())));
    }

    @Test
    void givenFindByActive_whenGetFinByActiveDoNotExist_thenReturnJsonNotFound() throws Exception {
        given(service.findByActive(false)).willThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/active/" + "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void givenFindByLastActive_whenGetFindByLastActive_thenReturnJson() throws Exception {
        List<AdminDto> allRoles = Arrays.asList(adminDto);

        given(service.findByLastActive(adminDto.getLastActive())).willReturn(allRoles);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/lastActive/" + adminDto.getLastActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastActive", is(adminDto.getLastActive().toString())));
    }

    @Test
    void givenFindByLastActive_whenGetFindByLastActiveDoNotExist_thenReturnJsonNotFound() throws Exception {
        given(service.findByLastActive(LocalDate.of(2020, 01, 01))).willThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/admin/lastActive/" + "2020-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void create() throws Exception {
        AdminDto toCreate = new AdminDto(null, "firstname2", "lastname2", "f@l.com2",
                "password2", "Admin2", true,
                LocalDate.of(2020, 12, 23));

        AdminDto created = new AdminDto("1", "firstname2", "lastname2", "f@l.com2",
                "password2", "Admin2", true,
                LocalDate.of(2020, 12, 23));

        given(service.create(toCreate)).willReturn(created);

        mvc.perform(MockMvcRequestBuilders.post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAdminDto.write(toCreate).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("adminId", is(created.getAdminId())));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}