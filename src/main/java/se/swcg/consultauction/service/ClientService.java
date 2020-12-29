package se.swcg.consultauction.service;


import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;
import java.util.List;

public interface ClientService {

    List<ClientDto> findAll();

    ClientDto findById(String id);

    List<ClientDto> findByCompanyName(String companyName);

    ClientDto findByEmail(String email);

    ClientDto createByForm(ClientForm clientForm);

    ClientDto update(ClientForm clientForm);

    boolean delete(String  id);
}
