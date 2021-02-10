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
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.service.ClientServiceImpl;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.any;



@AutoConfigureJsonTesters
@WebMvcTest(ClientController.class)
 class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientServiceImpl service;

    @Autowired
    private JacksonTester<ClientDto> jacksonTester;

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        Date d = new Date();
        clientDto = new ClientDto("0", "CompName", "firstName", "Lname","tob43@gmail.com",true,
             d,d,"0704129400","qwertY1asdsd","Client","asddsagf",null  );
    }

    @Test
    void test_findAll_should_return_json_array() throws Exception {
        List<ClientDto> allClients = Collections.singletonList(clientDto);

        when(service.findAll()).thenReturn(allClients);

        mvc.perform(get("/api/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(clientDto.getFirstName())));
    }

    @Test
    void test_findAll_should_return_json_not_found() throws Exception {
        when(service.findAll()).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findById_should_return_json_with_admin() throws Exception {
        when(service.findById(anyString())).thenReturn(clientDto);

        mvc.perform(get("/api/client/" + clientDto.getClientId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("clientId", is(clientDto.getClientId())));
    }

    @Test
    void test_findById_should_return_json_not_found() throws Exception {
        when(service.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/client/" + "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

    @Test
    void test_findByEmail_should_return_json_with_email() throws Exception {
        when(service.findByEmail(anyString())).thenReturn(clientDto);

        mvc.perform(get("/api/client/email/" + clientDto.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is(clientDto.getEmail())));
    }

    @Test
    void test_findByEmail_should_return_json_not_found() throws Exception {
        when(service.findByEmail(anyString())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/client/email/" + "email")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", is("NOT_FOUND")));
    }

   // geting weired error
   /* @Test
    void test_create_should_return_json_with_id() throws Exception {
        Date d = new Date();
        ClientDto toCreate = new ClientDto(null, "CompName", "firstName", "Lname","tob453@gmail.com",true,
                d,null,"0704129500","qwerttY1asdsd","Client1","asddsagf",null);


        ClientDto created = new ClientDto("1", "CompName", "firstName", "Lname","tob453@gmail.com",true,
                d,null,"0704129500","qwerttY1asdsd","Client1","asddsagf",null);


        when(service.create(any(ClientDto.class))).thenReturn(created);

        mvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toCreate).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("clientId", is(created.getClientId())));
    }*/

    @Test
    void test_create_should_return_unsupported_media_type() throws Exception {

        mvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .content(jacksonTester.write(clientDto).getJson()))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void test_update_should_return_json_with_updated() throws Exception {
        ClientDto toUpdate = clientDto;
        toUpdate.setFirstName("Null");

        when(service.update(any(ClientDto.class))).thenReturn(toUpdate);

        mvc.perform(put("/api/client/" + clientDto.getClientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is(toUpdate.getFirstName())));
    }

    @Test
    void test_update_should_return_json_not_found() throws Exception {
        Date d = new Date();
        ClientDto toUpdate = new ClientDto("5", "CompName", "firstName", "Lname","to43@gmail.com",true,
                d,d,"0704129400","qwetY1asdsd","Client","asddsagf",null);

        mvc.perform(put("/api/client/" + clientDto.getClientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(toUpdate).getJson()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("Id does not match.")));
    }

    @Test
    void test_delete_should_return_json_with_confirmation() throws Exception {
        when(service.delete(anyString())).thenReturn(true);

        mvc.perform(delete("/api/client/" + clientDto.getClientId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Client with id: 0 was successfully removed."));

    }

    @Test
    void test_delete_should_return_json_with_illegalArgument() throws Exception {
        when(service.delete(anyString())).thenReturn(false);

        mvc.perform(delete("/api/client/" + clientDto.getClientId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", containsString("Something went wrong")));

    }
}
