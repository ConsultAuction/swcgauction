/*
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
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
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
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Autowired
    private JacksonTester<UserDto> jacksonTester;

    private UserDto testObject;

    @BeforeEach
    void setUp() {
        List<Experience> experiences1 = new ArrayList<>();
        experiences1.add(new Experience("2", "Never worked."));
        List<Languages> languages1 = new ArrayList<>();
        languages1.add(new Languages("1" , "Java"));
        Address address1 = new Address("Road 1", "sweden" , "Stockholm" , "08");
        testObject = new UserDto("0" ,"Robin", "sandberg", "robin@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD!" , "user",
                "0704729300", "/image.jpg", 25, address1, new Qualifications("4",true, false, experiences1, languages1));
    }

    @Test
    void test_findAll_should_return_json_array() throws Exception {
        List<UserDto> allUsers = Collections.singletonList(testObject);

        when(service.findAll()).thenReturn(allUsers);

        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(testObject.getFirstName())));
    }

    @Test
    void test_findAll_should_return_json_not_found() throws Exception {
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_user() throws Exception {
        when(service.findById(anyString())).thenReturn(testObject);

        mvc.perform(get("/api/user/" + testObject.getUserId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("userId", is(testObject.getUserId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user/" + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByLanguage_should_return_json_array() throws Exception {
        List<UserDto> foundUsers = Collections.singletonList(testObject);

        when(service.findByLanguage(anyString())).thenReturn(foundUsers);

        mvc.perform(get("/api/user/language/" + testObject.getQualifications().getLanguage().get(0).getLanguage())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath( "$[0].qualifications.language.[0].language", is(testObject.getQualifications().getLanguage().get(0).getLanguage())));
    }

    @Test
    void test_findByLanguage_should_return_json_not_found() throws Exception {

        when(service.findByLanguage(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user/language/" + ".Net")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByLastActive_should_return_json_array() throws Exception {
        List<UserDto> foundUsers = Collections.singletonList(testObject);

        when(service.findByLastActive(any(LocalDate.class))).thenReturn(foundUsers);

        mvc.perform(get("/api/user/lastActive/" + testObject.getLastActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastActive", is(testObject.getLastActive().toString())));
    }

    @Test
    void test_findByLastActive_should_return_json_not_found() throws Exception {

        when(service.findByLastActive(any(LocalDate.class))).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user/lastActive/" + "2018-10-10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findAllByActive_should_return_json_array() throws Exception {
        List<UserDto> foundUsers = Collections.singletonList(testObject);

        when(service.findAllByActive(anyBoolean())).thenReturn(foundUsers);

        mvc.perform(get("/api/user/active/" + testObject.isActive())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].active", is(testObject.isActive())));
    }

    @Test
    void test_findAllByActive_should_return_json_not_found() throws Exception {

        when(service.findAllByActive(anyBoolean())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user/active/" + "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByavailable_should_return_json_array() throws Exception {
        List<UserDto> foundUsers = Collections.singletonList(testObject);

        when(service.findByAvailable(anyBoolean())).thenReturn(foundUsers);

        mvc.perform(get("/api/user/available/" + testObject.isAvailable())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].available", is(testObject.isAvailable())));
    }

    @Test
    void test_findByAvailable_should_return_json_not_found() throws Exception {

        when(service.findByAvailable(anyBoolean())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/user/available/" + "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_create_should_return_json_with_id() throws Exception {

        UserDto toCreate = new UserDto(null,"Sven", "Svensson", "sven@hotmail.com" , true, LocalDate.now(),  LocalDate.now(), true, "1234abcD!" , "user",
                "0734721597", "/image.jpg", 25, null, null);

        UserDto created = new UserDto("1",  "Sven", "Svensson", "sven@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD!" , "user",
                "0734721597", "/image.jpg", 25, null, null);


        when(service.create(any(UserDto.class))).thenReturn(created);

        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toCreate).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("userId", is(created.getUserId())));
    }

    @Test
    void test_create_should_return_unsupported_media_type() throws Exception {

        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .content(jacksonTester.write(testObject).getJson()))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void test_update_should_return_json_with_updated() throws Exception {
        UserDto toUpdate = testObject;
        toUpdate.setFirstName("Bengt");

        when(service.update(any(UserDto.class))).thenReturn(toUpdate);

        mvc.perform(put("/api/user/" + testObject.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is(toUpdate.getFirstName())));
    }

    @Test
    void test_update_should_return_json_not_found() throws Exception {
        UserDto toUpdate = new UserDto("1",  "Sven", "Svensson", "sven@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD!" , "user",
                "0734721597", "/image.jpg", 25, null, null);

        mvc.perform(put("/api/user/" + testObject.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("Id does not match.")));
    }

    @Test
    void test_delete_should_return_json_with_confirmation() throws Exception {
        when(service.delete(anyString())).thenReturn(true);

        mvc.perform(delete("/api/user/" + testObject.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("User with id: 0 was successfully removed."));

    }

    @Test
    void test_delete_should_return_json_with_illegalArgument() throws Exception {
        when(service.delete(anyString())).thenReturn(false);

        mvc.perform(delete("/api/user/" + testObject.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", containsString("Something went wrong")));

    }


}*/
