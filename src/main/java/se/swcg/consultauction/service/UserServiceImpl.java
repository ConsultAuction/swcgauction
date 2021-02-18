package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ConsultantDetails;
import se.swcg.consultauction.entity.Contact;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.model.CreateClientRequest;
import se.swcg.consultauction.model.CreateConsultantRequest;
import se.swcg.consultauction.repository.ConsultantDetailsRepository;
import se.swcg.consultauction.repository.UserRepository;
import se.swcg.consultauction.security.SecurityConstants;
import se.swcg.consultauction.security.SecurityUser;

import java.time.LocalDate;
import java.util.List;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    ConsultantDetailsRepository consultantDetailsRepository;
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
                userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with email: " + email)));
    }

    //@Override
    public Object findById(String userId) {
        /*return converter.userToDto(
                userRepo.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId)));*/

        UserDto foundUser = converter.userToDto(
                                userRepo.findById(userId)
                                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId)));

        if (foundUser.getRole().equals(SecurityConstants.ROLE_CONSULTANT)) {

            return consultantDetailsRepository.findByUserUserId(foundUser.getUserId())
                                                .orElseThrow(() -> new ResourceNotFoundException("Could not find a details with id: " + foundUser.getUserId()));
        }

        return foundUser;
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
    public List<User> findByRole(String role) {
        return null;
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
    public List<UserDto> findByAvailable(boolean available) {
        /*return checkIfListIsEmpty(
                converter.userToDto(
                        repo.findByAvailableForHire(available)), "Could not find any users with available status: " + available);*/
        return null;
    }


    @Override
    public UserDto createClient(CreateClientRequest clientRequest) {
        LocalDate todayDate = LocalDate.now();

        if (userRepo.findByEmail(clientRequest.getEmail()).isPresent()) throw new IllegalArgumentException("Email does already exists: " + clientRequest.getEmail());

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
                userRepo.save(newClient));
    }

    @Override
    public ConsultantDetails createConsultant(CreateConsultantRequest consultantRequest) {
        LocalDate todayDate = LocalDate.now();

        if (userRepo.findByEmail(consultantRequest.getEmail()).isPresent()) throw new IllegalArgumentException("Email does already exists: " + consultantRequest.getEmail());

        User newClient = new User(
                null,
                consultantRequest.getFirstName(),
                consultantRequest.getLastName(),
                consultantRequest.getEmail(),
                bCryptPasswordEncoder.encode(consultantRequest.getPassword()),
                SecurityConstants.ROLE_CONSULTANT,
                todayDate,
                todayDate,
                SecurityConstants.DEFAULT_ACTIVE,
                consultantRequest.getImage(),
                new Contact(
                        consultantRequest.getAddress(),
                        consultantRequest.getZipCode(),
                        consultantRequest.getCity(),
                        consultantRequest.getCountry(),
                        consultantRequest.getPhoneNumber()
                )
        );

        //newClient = userRepo.save(newClient);

        ConsultantDetails newDetails = new ConsultantDetails(
                consultantRequest.isFrontend(),
                consultantRequest.isBackend(),
                consultantRequest.isAvailableForHire(),
                consultantRequest.getMinPrice(),
                newClient,
                consultantRequest.getExperience(),
                consultantRequest.getLanguage(),
                consultantRequest.getSkills()
        );


        return consultantDetailsRepository.save(newDetails);
    }

    @Override
    public UserDto updateClient(String id, CreateClientRequest clientRequest) {
        User foundUser = converter.dtoToUser((UserDto) findById(id));

        if (userRepo.findByEmail(clientRequest.getEmail()).isPresent()) throw new IllegalArgumentException("New Email does already exist");

        foundUser.setCompanyName(clientRequest.getCompanyName());
        foundUser.setFirstName(clientRequest.getFirstName());
        foundUser.setLastName(clientRequest.getLastName());
        foundUser.setEmail(clientRequest.getEmail());
        foundUser.setPassword(bCryptPasswordEncoder.encode(clientRequest.getPassword()));
        foundUser.setImage(clientRequest.getImage());

        foundUser.getContact().setAddress(clientRequest.getAddress());
        foundUser.getContact().setZipCode(clientRequest.getZipCode());
        foundUser.getContact().setCity(clientRequest.getCity());
        foundUser.getContact().setCountry(clientRequest.getCountry());
        foundUser.getContact().setPhoneNumber(clientRequest.getPhoneNumber());

        return converter.userToDto(userRepo.save(foundUser));
    }

    @Override
    public UserDto updateConsultant(String id, CreateConsultantRequest createConsultantRequest) {
        return null;
    }

    @Override
    public boolean delete(String id) {

        userRepo.delete(converter.dtoToUser((UserDto) findById(id)));
        return  !userRepo.findById(id).isPresent();
    }
}
