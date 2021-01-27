package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Address;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ClientRepository;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientDtoConversionService converter;



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
        return converter.clientToDto(
                clientRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with email: " + email)));
    }

    @Override
    public ClientDto create(ClientDto dto) {
        checkIfValid(dto);

        return converter.clientToDto(clientRepository.save(converter.dtoToClient(dto)));

    }

    @Override
    public ClientDto update(ClientDto dto) {
        if (dto.getClientId() == null) {
            throw new IllegalArgumentException("Invalid id for admin: update");
        }

        Client foundClient = converter.dtoToClient(findById(dto.getClientId()));
        Client updatedClient = converter.dtoToClient(dto);

        //Check to not throw exception if email is the same
        if (!foundClient.getEmail().equals(dto.getEmail())) checkIfValid(dto);

        foundClient.setCompanyName(updatedClient.getCompanyName());
        foundClient.setFirstName(updatedClient.getFirstName());
        foundClient.setLastName(updatedClient.getLastName());
        foundClient.setEmail(updatedClient.getEmail());
        foundClient.setActive(updatedClient.isActive());
        foundClient.setDateForSignUp(updatedClient.getDateForSignUp());
        foundClient.setLastActive(updatedClient.getLastActive());
        foundClient.setPhoneNumber(updatedClient.getPhoneNumber());
        foundClient.setPassword(updatedClient.getPassword());
        foundClient.setRole(updatedClient.getRole());
        foundClient.setImage(updatedClient.getImage());
        foundClient.setAddress(updatedClient.getAddress());


        return converter.clientToDto(clientRepository.save(foundClient));


    }



    @Override
    public boolean delete(String  id) {
         clientRepository.delete(converter.dtoToClient(findById(id)));

         return !clientRepository.findById(id).isPresent();
    }
    private ClientDto checkIfValid(ClientDto dto) {
        if (clientRepository.findByEmail(dto.getEmail()).isPresent()) throw new IllegalArgumentException("Email already exists :" + dto.getEmail());

        return dto;
    }
}
