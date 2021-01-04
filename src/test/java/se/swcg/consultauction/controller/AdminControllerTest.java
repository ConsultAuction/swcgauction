package se.swcg.consultauction.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.AdminServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyBoolean;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdminServiceImpl service;

    @Autowired
    private JacksonTester<AdminDto> jacksonTester;

    private AdminDto adminDto;

    @BeforeEach
    void setUp() {
        adminDto = new AdminDto("0", "firstname", "lastname", "f@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));
    }

    @Test
    void test_findAll_should_return_json_array() throws Exception {
        List<AdminDto> allAdmins = Collections.singletonList(adminDto);

        when(service.findAll()).thenReturn(allAdmins);

        mvc.perform(get("/api/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(adminDto.getFirstName())));
    }

    @Test
    void test_findAll_should_return_json_not_found() throws Exception {
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_admin() throws Exception {
        when(service.findById(anyString())).thenReturn(adminDto);

        mvc.perform(get("/api/admin/" + adminDto.getAdminId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("adminId", is(adminDto.getAdminId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin/" + "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByEmail_should_return_json_with_email() throws Exception {
        when(service.findByEmail(anyString())).thenReturn(adminDto);

        mvc.perform(get("/api/admin/email/" + adminDto.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is(adminDto.getEmail())));
    }

    @Test
    void test_findByEmail_should_return_json_not_found() throws Exception {
        when(service.findByEmail(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin/email/" + "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByRole_should_return_json_array_with_role() throws Exception {
        List<AdminDto> allRoles = Collections.singletonList(adminDto);

        when(service.findByRole(anyString())).thenReturn(allRoles);

        mvc.perform(get("/api/admin/role/" + adminDto.getRole())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].role", is(adminDto.getRole())));
    }

    @Test
    void test_findByRole_should_return_json_not_found() throws Exception {
        when(service.findByRole(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin/role/" + "null")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByActive_should_return_json_array_with_active() throws Exception {
        List<AdminDto> allRoles = Collections.singletonList(adminDto);

        when(service.findByActive(anyBoolean())).thenReturn(allRoles);

        mvc.perform(get("/api/admin/active/" + adminDto.isActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].active", is(adminDto.isActive())));
    }

    @Test
    void test_findByActive_should_return_json_not_found() throws Exception {
        when(service.findByActive(anyBoolean())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin/active/" + "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByLastActive_should_return_json_array_with_last_active() throws Exception {
        List<AdminDto> allRoles = Collections.singletonList(adminDto);

        when(service.findByLastActive(any(LocalDate.class))).thenReturn(allRoles);

        mvc.perform(get("/api/admin/lastActive/" + adminDto.getLastActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastActive", is(adminDto.getLastActive().toString())));
    }

    @Test
    void test_findByLastActive_should_return_json_not_found() throws Exception {
        when(service.findByLastActive(any(LocalDate.class))).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/admin/lastActive/" + "2020-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_create_should_return_json_with_id() throws Exception {
        AdminDto toCreate = new AdminDto(null, "firstname2", "lastname2", "f@l.com2",
                "kalmarSummer2020!", "Admin2", true,
                LocalDate.of(2020, 12, 23));

        AdminDto created = new AdminDto("1", "firstname2", "lastname2", "f@l.com2",
                "kalmarSummer2020!", "Admin2", true,
                LocalDate.of(2020, 12, 23));


        when(service.create(any(AdminDto.class))).thenReturn(created);

        mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toCreate).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("adminId", is(created.getAdminId())));
    }

    @Test
    void test_create_should_return_unsupported_media_type() throws Exception {

        mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .content(jacksonTester.write(adminDto).getJson()))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void test_update_should_return_json_with_updated() throws Exception {
        AdminDto toUpdate = adminDto;
        toUpdate.setFirstName("Null");

        when(service.update(any(AdminDto.class))).thenReturn(toUpdate);

        mvc.perform(put("/api/admin/" + adminDto.getAdminId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is(toUpdate.getFirstName())));
    }

    @Test
    void test_update_should_return_json_not_found() throws Exception {
        AdminDto toUpdate = new AdminDto("1", "firstname", "lastname", "f@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

        mvc.perform(put("/api/admin/" + adminDto.getAdminId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("Id does not match.")));
    }

    @Test
    void test_delete_should_return_json_with_confirmation() throws Exception {
        when(service.delete(anyString())).thenReturn(true);

        mvc.perform(delete("/api/admin/" + adminDto.getAdminId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Admin with id: 0 was successfully removed."));

    }

    @Test
    void test_delete_should_return_json_with_illegalArgument() throws Exception {
        when(service.delete(anyString())).thenReturn(false);

        mvc.perform(delete("/api/admin/" + adminDto.getAdminId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", containsString("Something went wrong")));

    }
}