package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;

public interface DtoConversionService {

    Client dtoToClient(ClientDto dto);
    ClientDto clientToDto(Client client);
    Collection<ClientDto> clientToDto(Collection<Client> clients);

}
