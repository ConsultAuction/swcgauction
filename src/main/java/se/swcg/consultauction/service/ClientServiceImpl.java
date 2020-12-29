package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ClientRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


   private ClientRepository clientRepository;

   private ClientDtoConversionService converter;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientDtoConversionService converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }


    @Override
    public List<ClientDto> findAll() {
        return converter.clientToDto(clientRepository.findAll());
    }

    @Override
    public ClientDto findById(String id)  {
        return converter.clientToDto(clientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Could not find a client with ID: " + id)));
    }

    @Override
    public List<ClientDto> findByCompanyName(String companyName) {
        return converter.clientToDto(clientRepository.findByCompanyNameIgnoreCase(companyName));
    }

    @Override
    public ClientDto findByEmail(String email) {
        return converter.clientToDto(clientRepository.findByEmail(email));
    }

    @Override
    public ClientDto createByForm(ClientForm clientForm) {

        if (clientForm.getClientId() != null){
            throw new IllegalArgumentException("Invalid Client ID: ID should be specified at creation.");
        }

        return converter.clientToDto(clientRepository.save(converter.clientFormToClient(clientForm)));
    }

    @Override
    public ClientDto update(ClientForm clientForm) {
        if (clientForm.getClientId() == null){
            throw new IllegalArgumentException("Client had a Invalid ID: ");
        }
        Client client = clientRepository.findById(clientForm.getClientId()).orElseThrow(() -> new ResourceNotFoundException(
                "Could not find Client with ID: " + clientForm.getClientId()));

        Client updated = converter.clientFormToClient(clientForm);

        client.setCompanyName(updated.getCompanyName());
        client.setFirstName(updated.getFirstName());
        client.setLastName(updated.getLastName());
        client.setEmail(updated.getEmail());
        client.setActive(updated.isActive());
        client.setLastActive(updated.getLastActive());
        client.setPhoneNumber(updated.getPhoneNumber());
        client.setImage(updated.getImage());

        return converter.clientToDto(clientRepository.save(client));


    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }


}
