package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.Contact;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.model.CreateClientRequest;
import se.swcg.consultauction.model.CreateConsultantRequest;
import se.swcg.consultauction.repository.UserRepository;
import se.swcg.consultauction.security.SecurityConstants;
import se.swcg.consultauction.security.SecurityUser;

import java.time.LocalDate;
import java.util.List;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;
    @Autowired
    DtoConversionService converter;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /*//Constructor not working with test right now.
    @Autowired
    public UserServiceImpl(UserRepository repo, DtoConversionService converter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.converter = converter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SecurityUser(
                repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with email: " + email)));
    }

    @Override
    public UserDto findById(String userId) {
        return converter.userToDto(
                repo.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId)));
    }

    @Override
    public List<UserDto> findAll() {
        return checkIfListIsEmpty(converter.userToDto(repo.findAll()), "Could not find any users");
    }

    @Override
    public List<UserDto> findByLanguage(String language) {
        /*List<UserDto> foundUsers = converter.userToDto(repo.findByQualificationsLanguageLanguageIgnoreCase(language));

        // depending if we want to send back empty list or not
        if(foundUsers.isEmpty()){
            throw new ResourceNotFoundException("Could not find any users with language: " + language);
        }
        return foundUsers;*/
        return null;
    }


    @Override
    public UserDto findByEmail(String email) {
        return converter.userToDto(
                repo.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with email: " + email)));
    }

    @Override
    public List<User> findByRole(String role) {
        return null;
    }

    @Override
    public List<UserDto> findByLastActive(LocalDate lastActive) {
        return checkIfListIsEmpty(
                converter.userToDto(
                        repo.findByLastActive(lastActive)), "Could not find any users with last active date: " + lastActive);
    }

    @Override
    public List<UserDto> findByActive(boolean active) {
        return checkIfListIsEmpty(
                converter.userToDto(
                        repo.findByActive(active)), "Could not find any users with active status: " + active);
    }

    @Override
    public List<UserDto> findByAvailable(boolean available) {
        return checkIfListIsEmpty(
                converter.userToDto(
                        repo.findByAvailableForHire(available)), "Could not find any users with available status: " + available);
    }


    @Override
    public UserDto createClient(CreateClientRequest clientRequest) {
        LocalDate todayDate = LocalDate.now();

        if (repo.findByEmail(clientRequest.getEmail()).isPresent()) throw new IllegalArgumentException("Email does already exists: " + clientRequest.getEmail());

        User newClient = new User(
                clientRequest.getCompanyName(),
                clientRequest.getFirstName(),
                clientRequest.getLastName(),
                clientRequest.getEmail(),
                bCryptPasswordEncoder.encode(clientRequest.getPassword()),
                SecurityConstants.ROLE_CLIENT,
                todayDate,
                todayDate,
                SecurityConstants.DEFAULT_ACTIVE,
                SecurityConstants.DEFAULT_AVAILABLE_FOR_HIRE,
                clientRequest.getImage(),
                new Contact(
                        clientRequest.getAddress(),
                        clientRequest.getZipCode(),
                        clientRequest.getCity(),
                        clientRequest.getCountry(),
                        clientRequest.getPhoneNumber()
                )
        );

        return converter.userToDto(
                repo.save(newClient));
    }

    @Override
    public UserDto createConsultant(CreateConsultantRequest createConsultantRequest) {
        /*private String role;
    private LocalDate dateOfSignUp;
    private LocalDate lastActive;
    private boolean active;
    // Maybe set default for false?
    private boolean availableForHire;*/
        return null;
    }

    @Override
    public UserDto updateClient(CreateClientRequest createClientRequest) {
        return null;
    }

    @Override
    public UserDto updateConsultant(CreateConsultantRequest createConsultantRequest) {
        return null;
    }

    @Override
    public boolean delete(String id) {

        repo.delete(converter.dtoToUser(findById(id)));
        return  !repo.findById(id).isPresent();
    }
}
