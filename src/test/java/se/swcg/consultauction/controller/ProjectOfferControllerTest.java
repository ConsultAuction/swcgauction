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

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.DtoConversionServiceImpl;
import se.swcg.consultauction.service.ProjectOfferServiceImpl;

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
@WebMvcTest(ProjectOfferController.class)
public class ProjectOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectOfferServiceImpl service;

    @MockBean
    private DtoConversionServiceImpl converter;

    @Autowired
    private JacksonTester<ProjectOfferDto> jacksonTester;

    private ProjectOfferDto projectOfferDto;
    private User user;

    @BeforeEach
    void setup(){
        user = new User("1","lexicon","Tobias","hak","sten123@gmail.com","Asdffg132@","client",null,
                null,true,null,null, null);
        projectOfferDto = new ProjectOfferDto("0",null,null,false,false,
                null,false,null);
    }

    @Test
    void test_findAll_should_return_json_list() throws Exception{
        List<ProjectOfferDto> allProjects = Collections.singletonList(projectOfferDto);

        when(service.findAll()).thenReturn(allProjects);

        mvc.perform(get("/api/projectOffer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    void test_findAll_should_return_json_not_found() throws Exception{
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/projectOffer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_id() throws Exception {
        when(service.findById(anyString())).thenReturn(projectOfferDto);

        mvc.perform(get("/api/projectOffer/" + projectOfferDto.getProjectOfferId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("projectOfferId", is(projectOfferDto.getProjectOfferId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/projectOffer/" + projectOfferDto.getProjectOfferId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByAcceptedByUserId_should_return_json_with_acceptedByUserId() throws Exception{

        List<ProjectOfferDto> allProjects = Collections.singletonList(projectOfferDto);

        when(service.findByAcceptedByUserId(converter.userToDto(user))).thenReturn(allProjects);

        mvc.perform(get("/api/projectOffer/"+ projectOfferDto.getUser())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("projectOfferUser", is(projectOfferDto.getUser())));
    }

}
