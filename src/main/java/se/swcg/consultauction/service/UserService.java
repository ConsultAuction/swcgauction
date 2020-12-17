package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.Collection;

public interface UserService {

    UserDto create(UserForm userForm);
    UserDto findById(String userId);
    Collection<UserDto> findAll();
    Collection<UserDto> findByLanguage(String language);
    Collection<UserDto> findByLastActive(LocalDate lastActive);
    Collection<UserDto> findAllByActive(boolean active);
    Collection<UserDto> findByAvailable(boolean available);
    UserDto update(UserForm userForm);
    void delete(User user);

}
