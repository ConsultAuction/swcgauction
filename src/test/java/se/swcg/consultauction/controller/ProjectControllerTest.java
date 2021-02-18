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
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.ProjectServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectServiceImpl service;

    @Autowired
    private JacksonTester<ProjectDto> jacksonTester;

    private ProjectDto projectDto;

    @BeforeEach
    void setup(){
        projectDto = new ProjectDto("0","Test",null,null,4,
                "testson","kalmar",true,false,"testa",
               "sten123@gmail.com","0704129400",
                null);
    }

    @Test
    void test_findAll_should_return_json_list() throws Exception{
        List<ProjectDto> allProjects = Collections.singletonList(projectDto);

        when(service.findAll()).thenReturn(allProjects);

        mvc.perform(get("/api/project")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    void test_findAll_should_return_json_not_found() throws Exception{
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/project")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_id() throws Exception {
        when(service.findById(anyString())).thenReturn(projectDto);

        mvc.perform(get("/api/project/" + projectDto.getProjectId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("projectId", is(projectDto.getProjectId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/project/" + projectDto.getProjectId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByContactEmail_should_return_json_with_email() throws Exception {
        when(service.findById(anyString())).thenReturn(projectDto);

        mvc.perform(get("/api/project/" + projectDto.getContactEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("contactEmail", is(projectDto.getContactEmail())));
    }
    @Test
    void test_findByContactEmail_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/project/" + projectDto.getContactEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }


}
