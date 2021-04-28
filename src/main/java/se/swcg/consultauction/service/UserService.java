package se.swcg.consultauction.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.model.CreateClientRequest;
import se.swcg.consultauction.model.CreateRequest;
import se.swcg.consultauction.model.CreateConsultantRequest;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends UserDetailsService{

    UserDto findById(String userId);
    List<UserDto> findAll();
    List<UserDto> findByLanguage(String language);
    UserDto findByEmail(String email);
    List<UserDto> findAllConsultants();
    List<UserDto> findByLastActive(LocalDate lastActive);
    List<UserDto> findByActive(boolean active);
    List<UserDto> findByAvailable();

    UserDto createClient(CreateRequest createRequest);
    UserDto createConsultant(CreateRequest createRequest);

    UserDto updateClient(String id, CreateClientRequest createClientRequest);
    UserDto updateConsultant(String id, CreateConsultantRequest createConsultantRequest);

    boolean delete(String id);

}
