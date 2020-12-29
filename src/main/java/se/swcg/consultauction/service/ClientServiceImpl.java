package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ClientRepository;

import java.util.List;
import java.util.Optional;


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
    public List<ClientDto> findAll() {
            List<Client> clients = (List<Client>) clientRepository.findAll();
            if (clients.isEmpty()){
                throw new ResourceNotFoundException();
            } else {
                return converter.clientToDto(clients);
            }
    }

    @Override
    public ClientDto findById(String id)  {
        Optional<Client> optional =     clientRepository.findById(id);
        if (optional.isPresent()){
            Client client = optional.get();
            return converter.clientToDto(client);
        }
        else throw  new ResourceNotFoundException( "Could not find Client with ID: "+optional.get().getClientId());



    }

    @Override
    public List<ClientDto> findByCompanyName(String companyName) {
        List<Client> clients = (List<Client>) clientRepository.findByCompanyNameIgnoreCase(companyName);
        if (clients.isEmpty()){
            throw new ResourceNotFoundException();
        } else {
            return converter.clientToDto(clients);
        }

    }

    @Override
    public ClientDto findByEmail(String email) {
        return converter.clientToDto(clientRepository.findByEmail(email));
    }

    @Override
    public ClientDto createByForm(ClientForm clientForm) {

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
    public boolean delete(String  id) {

        Optional<Client> remove = clientRepository.findById(id);
        if (remove.isPresent()){
            Client old = remove.get();
            clientRepository.delete(old);
            return true;
        }
        return false;

    }
}
