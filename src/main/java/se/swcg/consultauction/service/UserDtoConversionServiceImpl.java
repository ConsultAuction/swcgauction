package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserDtoConversionServiceImpl implements UserDtoConversionService{

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
    public UserForm dtoToForm(UserDto dto) {
        return new UserForm(dto.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.isActive(), dto.getDateOfSignUp(), dto.getLastActive(), dto.isAvailable(),
                dto.getPassword(), dto.getRole(), dto.getPhoneNumber(), dto.getImage(), dto.getMinPrice(),
                dto.getAddress(), dto.getQualifications());
    }

    @Override
    public List<UserDto> userToDto(List<User> users) {
        if (users == null){
            users = new ArrayList<>();
        }
        List<UserDto> userDtos = new ArrayList<>();

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
