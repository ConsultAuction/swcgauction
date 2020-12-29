package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repo;
    UserDtoConversionServiceImpl converter;

    @Autowired
    public UserServiceImpl(UserRepository repo, UserDtoConversionServiceImpl converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public UserDto create(UserForm userForm) {
        //return converter.userToDto(repo.save(converter.userFormToUser(userForm)));
        UserDto dto = converter.userToDto(converter.userFormToUser(userForm));
        return converter.userToDto(repo.save(converter.dtoToUser(dto)));
    }

    @Override
    public UserDto findById(String userId) {
        return converter.userToDto(repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId)));
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> foundUsers = converter.userToDto(repo.findAll());

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users");
        }
        return foundUsers;
    }

    @Override
    public List<UserDto> findByLanguage(String language) {
        List<UserDto> foundUsers = converter.userToDto(repo.findByQualificationsLanguageLanguageIgnoreCase(language));

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users with language: " + language);
        }
        return foundUsers;
    }

    @Override
    public List<UserDto> findByLastActive(LocalDate lastActive) {
        List<UserDto> foundUsers = converter.userToDto(repo.findByLastActive(lastActive));

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users with last active date: " + lastActive);
        }
        return foundUsers;
    }

    @Override
    public List<UserDto> findAllByActive(boolean active) {
        List<UserDto> foundUsers = converter.userToDto(repo.findAllByActive(active));

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users with active status: " + active);
        }
        return foundUsers;
    }

    @Override
    public List<UserDto> findByAvailable(boolean available) {
        List<UserDto> foundUsers = converter.userToDto(repo.findByAvailable(available));

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users with available status: " + available);
        }
        return foundUsers;
    }

    @Override
    public UserDto update(UserForm userForm) {
       if (userForm.getUserId() == null){
            throw new IllegalArgumentException("User had a Invalid ID: ");
        }

        User foundUser = converter.dtoToUser(findById(userForm.getUserId()));

        User updatedUser = converter.userFormToUser(userForm);

        foundUser.setFirstName(updatedUser.getFirstName());
        foundUser.setLastName(updatedUser.getLastName());
        foundUser.setEmail(updatedUser.getEmail());
        foundUser.setActive(updatedUser.isActive());
        foundUser.setDateOfSignUp(updatedUser.getDateOfSignUp());
        foundUser.setLastActive(updatedUser.getLastActive());
        foundUser.setAvailable(updatedUser.isAvailable());
        foundUser.setPassword(updatedUser.getPassword());
        foundUser.setRole(updatedUser.getRole());
        foundUser.setPhoneNumber(updatedUser.getPhoneNumber());
        foundUser.setImage(updatedUser.getImage());
        foundUser.setMinPrice(updatedUser.getMinPrice());
        foundUser.setAddress(updatedUser.getAddress());
        foundUser.setQualifications(updatedUser.getQualifications());

        return converter.userToDto(repo.save(foundUser));
    }

    @Override
    public void delete(User user) {
        repo.delete(user);
    }
}
