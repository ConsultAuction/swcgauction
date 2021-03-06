package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ConsultantDetails;
import se.swcg.consultauction.entity.Contact;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.model.CreateClientRequest;
import se.swcg.consultauction.model.CreateRequest;
import se.swcg.consultauction.model.CreateConsultantRequest;
import se.swcg.consultauction.model.UpdatePassword;
import se.swcg.consultauction.repository.UserRepository;
import se.swcg.consultauction.security.SecurityConstants;
import se.swcg.consultauction.security.SecurityRoles;
import se.swcg.consultauction.security.SecurityUser;

import java.time.LocalDate;
import java.util.List;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepo;
    private DtoConversionService converter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, DtoConversionService converter, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SecurityUser(
                userRepo.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Could not find user with email: " + email)));
    }

    @Override
    public UserDto findById(String userId) {
        return converter.userToDto(
                userRepo.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId)));
    }

    @Override
    public List<UserDto> findAll() {
        return checkIfListIsEmpty(converter.userToDto(userRepo.findAll()), "Could not find any users");
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
                userRepo.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with email: " + email)));
    }

    @Override
    public List<UserDto> findAllConsultants() {

        return checkIfListIsEmpty(
                converter.userToDto(
                        userRepo.findAllByRole(SecurityRoles.CONSULTANT.name())
                ),
                "Could not find any consultants"
        );
    }

    @Override
    public List<UserDto> findByLastActive(LocalDate lastActive) {
        return checkIfListIsEmpty(
                converter.userToDto(
                        userRepo.findByLastActive(lastActive)), "Could not find any users with last active date: " + lastActive);
    }

    @Override
    public List<UserDto> findByActive(boolean active) {
        return checkIfListIsEmpty(
                converter.userToDto(
                        userRepo.findByActive(active)), "Could not find any users with active status: " + active);
    }

    @Override
    public List<UserDto> findByAvailable() {
        return checkIfListIsEmpty(
                converter.userToDto(
                        userRepo.findAllByConsultantDetailsAvailableForHire(true)), "Could not find any users with available status: ");
    }


    @Override
    public UserDto createClient(CreateRequest clientRequest) {
        LocalDate todayDate = LocalDate.now();

        if (userRepo.findByEmail(clientRequest.getEmail()).isPresent()) throw new IllegalArgumentException("Email does already exists: " + clientRequest.getEmail());

        User newClient = new User(
                null,
                null,
                null,
                clientRequest.getEmail(),
                passwordEncoder.encode(clientRequest.getPassword()),
                SecurityRoles.CLIENT.name(),
                todayDate,
                todayDate,
                SecurityConstants.DEFAULT_ACTIVE,
                null,
                new Contact(),
                null
        );

        return converter.userToDto(
                userRepo.save(newClient));
    }

    @Override
    public UserDto createConsultant(CreateRequest createRequest) {
        LocalDate todayDate = LocalDate.now();

        if (userRepo.findByEmail(createRequest.getEmail()).isPresent()) throw new IllegalArgumentException("Email does already exists: " + createRequest.getEmail());

        User newConsultant = new User(
                null,
                null,
                null,
                createRequest.getEmail(),
                passwordEncoder.encode(createRequest.getPassword()),
                SecurityRoles.CONSULTANT.name(),
                todayDate,
                todayDate,
                SecurityConstants.DEFAULT_ACTIVE,
                null,
                new Contact(),
                new ConsultantDetails()
        );




        return converter.userToDto(userRepo.save(newConsultant));
    }

    @Override
    public UserDto updateClient(String id, CreateClientRequest createClientRequest) {
        User foundUser = converter.dtoToUser(findById(id));

        //if (userRepo.findByEmail(clientRequest.getEmail()).isPresent()) throw new IllegalArgumentException("New Email does already exist");

        foundUser.setCompanyName(createClientRequest.getCompanyName());
        foundUser.setFirstName(createClientRequest.getFirstName());
        foundUser.setLastName(createClientRequest.getLastName());
        foundUser.setEmail(createClientRequest.getEmail());
        foundUser.setImage(createClientRequest.getImage());

        foundUser.getContact().setAddress(createClientRequest.getAddress());
        foundUser.getContact().setZipCode(createClientRequest.getZipCode());
        foundUser.getContact().setCity(createClientRequest.getCity());
        foundUser.getContact().setCountry(createClientRequest.getCountry());
        foundUser.getContact().setPhoneNumber(createClientRequest.getPhoneNumber());

        return converter.userToDto(userRepo.save(foundUser));
    }

    @Override
    public UserDto updatePassword(String id, UpdatePassword updatePassword) {
        User foundUser = converter.dtoToUser(findById(id));

        foundUser.setPassword(passwordEncoder.encode(updatePassword.getPassword()));

        return converter.userToDto(
                userRepo.save(foundUser)
        );
    }

    @Override
    public UserDto updateConsultant(String id, CreateConsultantRequest consultantRequest) {

        User foundUser = converter.dtoToUser(findById(id));

        foundUser.setFirstName(consultantRequest.getFirstName());

        foundUser.setLastName(consultantRequest.getLastName());
        foundUser.setEmail(consultantRequest.getEmail());
        foundUser.setImage(consultantRequest.getImage());

        foundUser.getContact().setAddress(consultantRequest.getAddress());
        foundUser.getContact().setZipCode(consultantRequest.getZipCode());
        foundUser.getContact().setCity(consultantRequest.getCity());
        foundUser.getContact().setCountry(consultantRequest.getCountry());
        foundUser.getContact().setPhoneNumber(consultantRequest.getPhoneNumber());

        foundUser.getConsultantDetails().setFrontend(consultantRequest.isFrontend());
        foundUser.getConsultantDetails().setBackend(consultantRequest.isBackend());
        foundUser.getConsultantDetails().setAvailableForHire(consultantRequest.isAvailableForHire());
        foundUser.getConsultantDetails().setMinPrice(consultantRequest.getMinPrice());
        foundUser.getConsultantDetails().setExperience(consultantRequest.getExperience());
        foundUser.getConsultantDetails().setLanguage(consultantRequest.getLanguage());
        foundUser.getConsultantDetails().setSkills(consultantRequest.getSkills());


        return converter.userToDto(userRepo.save(foundUser));
    }

    @Override
    public boolean delete(String id) {
        userRepo.delete(converter.dtoToUser(findById(id)));
        return  !userRepo.findById(id).isPresent();
    }
}
