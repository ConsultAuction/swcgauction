package se.swcg.consultauction.service;


import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Address;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.repository.ClientRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
 class ClientServiceTest {





    @Autowired
    private ClientService clientService;
    @Autowired
    private DtoConversionService convert;
    @Autowired
    private ClientRepository clientRepositoryMock;

     Client tobias;



    @BeforeEach
     void setup(){
        Date d = new Date();
        Address a = new Address("gdsfg","gamla vagen 51","Sweden","Trekanten","39245");
        tobias = new Client("g3l0df","SWCG","Tobias","Hakansson","tobias19995@gmail.com",
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
     void createByForm(){

        int size = 2;

     clientService.createByForm(new ClientForm("SWCGc","dfg","ASDFGH",
             "tobias199935@gmail.com", true, new Date(),new Date(),
             "0704129400","qwertY1asdsd", "Admin","zxcvbnm",null));


        List<ClientDto> res = clientService.findAll();

        ClientDto dto;

        assertEquals(size,res.size());
        for (ClientDto d: res){
            dto = d;
            assertFalse(dto.getClientId().isEmpty());
        }
    }

    @Test
    void update(){
        ClientForm updatedClient = convert.doToClientForm(convert.clientToDto(tobias));
        updatedClient.getAddress().setCity("Växjö");
        //Act
        tobias = convert.dtoToClient(clientService.update(updatedClient));
        //Assert
        assertEquals(updatedClient.getAddress(), tobias.getAddress());

    }

    @AfterEach
    void after(){
        clientRepositoryMock.deleteAll();
    }




}
