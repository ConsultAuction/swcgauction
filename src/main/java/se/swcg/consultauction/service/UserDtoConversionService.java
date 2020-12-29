package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;

import java.util.List;

public interface UserDtoConversionService {

    User dtoToUser(UserDto dto);
    UserDto userToDto(User user);
    UserForm dtoToForm(UserDto dto);
    List<UserDto> userToDto(List<User> users);

    User userFormToUser(UserForm dto);

}
