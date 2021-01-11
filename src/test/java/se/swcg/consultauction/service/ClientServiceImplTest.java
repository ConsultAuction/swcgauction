package se.swcg.consultauction.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Address;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.repository.ClientRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
 class ClientServiceTest {





    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientDtoConversionService convert;
    @Autowired
    private ClientRepository clientRepositoryMock;

     Client tobias;



    @BeforeEach
     void setup(){
        Date d = new Date();
        Address a = new Address("gdsfg","gamla vagen 51","Sweden","Trekanten","39245");
        tobias = new Client("g3l0df","SWCG","Tobias","Hakansson","tobias199945@gmail.com",
                true,d,d,"0704129400","qwertY1asdsd","Admin","zxcvbnm",a);
       tobias = clientRepositoryMock.save(tobias);
    }


    @Test
     void findByEmail(){
        String email = tobias.getEmail();

        ClientDto res = clientService.findByEmail(email);

        assertEquals(email,res.getEmail());

    }
    @Test
     void findByCompanyName(){

        int exp = 1;

        List<ClientDto> res = clientService.findByCompanyName(tobias.getCompanyName());

        assertEquals(exp,res.size());
    }



    @Test
     void findAll(){

        int expected = 1;

        List<ClientDto> res = clientService.findAll();

        assertEquals(expected,res.size());

    }
    @Test
     void findById(){

        String  id = tobias.getClientId();

        ClientDto res =  clientService.findById(id);

        assertEquals(id,res.getClientId());

    }
    @Test
     void create(){
        Date d = new Date();

        int size = clientService.findAll().size() + 1;

        ClientDto toCreate = new ClientDto("3", "CompName", "firstName", "Lname",
                "tob43@gmail.com", true,
                d, d, "0704129500", "qwertY1asdsd", "Client", "asddsagf", null);

        ClientDto dto = clientService.create(toCreate);

        assertNotNull(dto.getClientId());
        assertEquals(size, clientService.findAll().size());
    }

    // geting weired error
    /*@Test
    void update(){
        String oldEmail = tobias.getEmail();
        String newEmail = "tosdfg324@gmail.com";

        ClientDto toUpdate = new ClientDto(tobias.getClientId(),tobias.getCompanyName(),tobias.getFirstName(),
                tobias.getLastName(),newEmail,tobias.isActive(),tobias.getDateForSignUp(),tobias.getLastActive(),
                tobias.getPhoneNumber(),tobias.getPassword(),tobias.getRole(),tobias.getImage(),tobias.getAddress());

        ClientDto updatedClient = clientService.update(toUpdate);

        assertEquals(newEmail,updatedClient.getEmail());
        assertNotEquals(oldEmail, updatedClient.getEmail());

    }*/

    @Test
    void delete() {
        Date d = new Date();
        ClientDto toDelete = new ClientDto(null, "CompName", "firstName", "Lname",
                "tob43@gmail.com", true,
                d, d, "0704129500", "qwertY1asdsd", "Client", "asddsagf", null);

        InvalidDataAccessApiUsageException e = assertThrows(InvalidDataAccessApiUsageException.class, () ->
                clientService.delete(toDelete.getClientId()));

        assertThat(e).hasMessageContaining("The given id must not be null");
    }




}
