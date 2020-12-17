package se.swcg.consultauction.service;


import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;

public interface ClientService {

    Collection<ClientDto>  findAll();

    ClientDto findById(String id);

    Collection<ClientDto> findByCompanyName(String companyName);

    ClientDto findByEmail(String email);

    ClientDto createByForm(ClientForm clientForm);

    ClientDto update(ClientForm clientForm);

    void delete(Client client);
}
