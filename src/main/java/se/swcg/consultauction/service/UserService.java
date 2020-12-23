package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserDto create(UserForm userForm);
    UserDto findById(String userId);
    List<UserDto> findAll();
    List<UserDto> findByLanguage(String language);
    List<UserDto> findByLastActive(LocalDate lastActive);
    List<UserDto> findAllByActive(boolean active);
    List<UserDto> findByAvailable(boolean available);
    UserDto update(UserForm userForm);
    void delete(User user);

}
