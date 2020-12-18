package se.swcg.consultauction.service;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.entity.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class DtoConversionServiceImpl implements DtoConversionService {


    @Override
    public Client dtoToClient(ClientDto dto) {
        return new Client(dto.getId(),dto.getFirstName(),dto.getLastName(),dto.getLastName(),dto.getPhoneNumber(),
               dto.isActive(),dto.getDateForSignUp(),dto.getLastActive(),dto.getPhoneNumber(),
                dto.getPassword(),dto.getRole(),dto.getImage());
    }

    @Override
    public ClientDto clientToDto(Client client) {
        return new ClientDto(client.getClientId(),client.getCompanyName(),client.getFirstName(),
                client.getLastName(),client.getEmail(),client.isActive(),client.getDateForSignUp(),
                client.getLastActive(),client.getPhoneNumber(),client.getPassword(),client.getRole(),
                client.getImage());
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

    @Override
    public Client clientFormToClient(ClientForm dto) {

        return new Client(dto.getId(),dto.getCompanyName(),dto.getFirstName(),dto.getLastName(),
                dto.getEmail(), dto.isActive(), dto.getDateForSignUp(),dto.getLastActive(),
                dto.getPhoneNumber(),dto.getPassword(),dto.getRole(),dto.getImage());
    }

    @Override
    public User dtoToUser(UserDto dto) {
        return new User(dto.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.isActive(), dto.getDateOfSignUp(), dto.getLastActive(), dto.isAvailable(),
                dto.getPassword(), dto.getRole(), dto.getPhoneNumber(), dto.getImage(), dto.getMinPrice(),
                dto.getAddress(), dto.getQualifications());
    }

    @Override
    public UserDto userToDto(User user) {
        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.isActive(), user.getDateOfSignUp(), user.getLastActive(), user.isAvailable(),
                user.getPassword(), user.getRole(), user.getPhoneNumber(), user.getImage(), user.getMinPrice(),
                user.getAddress(), user.getQualifications());
    }

    @Override
    public Collection<UserDto> userToDto(Collection<User> users) {
        if (users == null){
            users = new ArrayList<>();
        }
        Collection<UserDto> userDtos = new ArrayList<>();

        for(User c: users){
            userDtos.add(userToDto(c));
        }
        return userDtos;
    }

    @Override
    public User userFormToUser(UserForm dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.isActive(), dto.getDateOfSignUp(), dto.getLastActive(), dto.isAvailable(),
                dto.getPassword(), dto.getRole(), dto.getPhoneNumber(), dto.getImage(), dto.getMinPrice(),
                dto.getAddress(), dto.getQualifications());
    }


}

