package se.swcg.consultauction.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.entity.Address;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.repository.ClientRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


@SpringBootTest
public class ClientServiceImplTest {

    @TestConfiguration
    static class ClientServiceImplTestContextConfiguration{
        @Primary
        @Bean
        public ClientService clientService(ClientRepository clientRepository, DtoConversionService dtoConversionService){
            return new ClientServiceImpl(clientRepository,dtoConversionService);
        }
    }


    @Autowired
    private ClientService clientService;
    @MockBean
    private DtoConversionService dtoMock;
    @MockBean
    private ClientRepository clientRepositoryMock;

    private ClientDto tobias;

    @BeforeEach
    public void setup(){
        Date d = new Date();
        Address a = new Address("gdsfg","gamla vagen 51","Sweden","Trekanten","39245");
        tobias = new ClientDto("g3l0df","SWCG","Tobias","Hakansson","tobias19995@gmail.com",
                true,d,d,"0704129400","qwerty","Admin","zxcvbnm",a);
    }


    @Test public void findByEmail(){


        when(clientService.findByEmail(anyString())).thenReturn(tobias);


        ClientDto result =  dtoMock.clientToDto(clientRepositoryMock.findByEmail(anyString()));

        assertEquals(tobias.getEmail(),result.getEmail());

    }

    @Test
    public void findAll(){

        when(clientService.findAll()).thenReturn(Arrays.asList(tobias));

        List<ClientDto> res = dtoMock.clientToDto(clientRepositoryMock.findAll());

        assertEquals(Arrays.asList(tobias),res);

    }
    @Test
    public void findById(){

        // wont work, don't know why

        when(clientService.findById(("g3l0df"))).thenReturn(tobias);



        assertEquals(tobias.getClientId(),res.getClientId());

    }




}
