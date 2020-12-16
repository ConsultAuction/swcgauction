package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.repository.ClientRepository;

import java.util.Collection;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    DtoConversionService converter;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, DtoConversionService converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }


    @Override
    public Collection<ClientDto> findAll() {
        return converter.clientToDto((Collection<Client>) clientRepository.findAll());
    }

    @Override
    public ClientDto findById(String id)  {
        return converter.clientToDto(clientRepository.findById(id).get());
    }

    @Override
    public Collection<ClientDto> findByCompanyName(String companyName) {
        return converter.clientToDto(clientRepository.findByCompanyNameIgnoreCase(companyName));
    }

    @Override
    public ClientDto findByEmail(String email) {
        return converter.clientToDto(clientRepository.findByEmail(email));
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
