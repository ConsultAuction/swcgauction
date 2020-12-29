package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.entity.User;

import java.util.Collection;
import java.util.List;

public interface ClientDtoConversionService {

    Client dtoToClient(ClientDto dto);

    ClientDto clientToDto(Client client);
    List<ClientDto> clientToDto(List<Client> clients);

    Client clientFormToClient(ClientForm dto);

    ClientForm DtoToForm(ClientDto dto);

}
