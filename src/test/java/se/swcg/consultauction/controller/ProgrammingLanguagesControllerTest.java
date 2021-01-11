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
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.ProgrammingLanguagesServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@WebMvcTest(ProgrammingLanguagesController.class)
class ProgrammingLanguagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProgrammingLanguagesServiceImpl service;

    @Autowired
    private JacksonTester<ProgrammingLanguagesDto> jacksonTester;

    private ProgrammingLanguagesDto languagesDto;

    @BeforeEach
    void setUp() {
        languagesDto = new ProgrammingLanguagesDto("0", "Java");
    }

    @Test
    void test_findAll_should_return_json_list() throws Exception{
        List<ProgrammingLanguagesDto> allLanguages = Collections.singletonList(languagesDto);

        when(service.findAll()).thenReturn(allLanguages);

        mvc.perform(get("/api/languages")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].language", is(languagesDto.getLanguage())));
    }

    @Test
    void test_findAll_should_return_json_not_found() throws Exception{
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/languages")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_language() throws Exception {
        when(service.findById(anyString())).thenReturn(languagesDto);

        mvc.perform(get("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("languageId", is(languagesDto.getLanguageId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_create_should_return_json_with_id() throws Exception {
        ProgrammingLanguagesDto toCreate = new ProgrammingLanguagesDto(null, "Java");
        ProgrammingLanguagesDto created = new ProgrammingLanguagesDto("1", "Java");

        when(service.create(any(ProgrammingLanguagesDto.class))).thenReturn(created);

        mvc.perform(post("/api/languages")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jacksonTester.write(toCreate).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("languageId", is(created.getLanguageId())));
    }

    @Test
    void test_create_should_return_unsupported_media_type() throws Exception {
        mvc.perform(post("/api/languages")
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .content(jacksonTester.write(languagesDto).getJson()))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void test_update_should_return_json_with_updated() throws Exception {
        ProgrammingLanguagesDto toUpdate = languagesDto;
        toUpdate.setLanguage("JavaScrip");

        when(service.update(any(ProgrammingLanguagesDto.class))).thenReturn(toUpdate);

        mvc.perform(put("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("language", is(toUpdate.getLanguage())));
    }

    @Test
    void test_update_should_return_json_not_found() throws Exception {
        ProgrammingLanguagesDto toUpdate = new ProgrammingLanguagesDto("2", "java");

        mvc.perform(put("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("Id does not match.")));
    }

    @Test
    void test_delete_should_return_json_with_confirmation() throws Exception {
        when(service.delete(anyString())).thenReturn(true);

        mvc.perform(delete("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Language with id: 0 was successfully removed."));
    }

    @Test
    void test_delete_should_return_json_with_illegalArgument() throws Exception {
        when(service.delete(anyString())).thenReturn(false);

        mvc.perform(delete("/api/languages/" + languagesDto.getLanguageId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", containsString("Something went wrong")));
    }
}