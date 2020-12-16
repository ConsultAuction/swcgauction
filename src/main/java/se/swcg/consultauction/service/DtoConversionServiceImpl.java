package se.swcg.consultauction.service;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.entity.Client;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class DtoConversionServiceImpl implements DtoConversionService {


    @Override
    public Client dtoToClient(ClientDto dto) {
        return new Client(dto.getId(),dto.getCompanyName(),dto.getFirstName(),dto.getLastName(),dto.getEmail(),
                dto.isActive(),dto.getDateForSignUp(),dto.getLastActive(),dto.getPhoneNumber(),dto.getPassword(),
                dto.getRole(),dto.getImage());
    }

    @Override
    public ClientDto clientToDto(Client client) {
        return new ClientDto(client.getId(),client.getCompanyName(),client.getFirstName(),client.getLastName(),
                client.getEmail(),client.isActive(),client.getDateForSignUp(),client.getLastActive(),
                client.getPhoneNumber(),client.getPassword(),client.getRole(),client.getImage());
    }

    @Override
    public Collection<ClientDto> clientToDto(Collection<Client> clients) {
       if (clients == null){
           clients = new ArrayList<>();
       }
       Collection<ClientDto> clientDtos = new ArrayList<>();

       for(Client c: clients){
           clientDtos.add(clientToDto(c));
       }
       return clientDtos;
    }
}
