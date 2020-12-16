package se.swcg.consultauction.service;


import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;

public interface ClientService {

    Collection<ClientDto>  findAll();

    ClientDto findById(String id);

    Collection<ClientDto> findByCompanyName(String companyName);

    ClientDto findByEmail(String email);




    void delete(Client client);
}
