/*
package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class UserServiceTest {

    @Autowired
    UserService testObject;
    @Autowired
    UserRepository repo;
    @Autowired
    DtoConversionService converter;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        List<Experience> experiences1 = new ArrayList<>();
        experiences1.add(new Experience("Never worked."));
        List<Languages> languages1 = new ArrayList<>();
        languages1.add(new Languages("Java"));
        List<Experience> experiences2 = new ArrayList<>();
        experiences2.add(new Experience("worked 25 years."));
        List<Languages> languages2 = new ArrayList<>();
        languages2.add(new Languages("Ruby"));
        Address address1 = new Address("Road 1", "sweden" , "Stockholm" , "08");
        Address address2 = new Address("Road 2", "sweden" , "malmö" , "047714");
        user1 = new User("Robin", "sandberg", "robin@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD!" , "user",
                "0704729300", "/image.jpg", 25, address1, new Qualifications(true, false, experiences1, languages1));
        user1 = repo.save(user1);
        user2 = new User("Sten" , "Stensson", "sten@hotmail.com" , false, LocalDate.now(), LocalDate.now(), false, "1234Dcsa!" , "user",
                "0704139500", "/image.jpg", 25, address2, new Qualifications(false, true, experiences2, languages2));
        user2 = repo.save(user2);

    }

    @Test
    void findByIdTest(){
        //Arrange
        String id = user1.getUserId();
        //Act
        UserDto result = testObject.findById(id);
        //Assert
        assertEquals(id, result.getUserId());
    }

    @Test
    void findAllTest(){
        //Arrange
        int size = 2;
        //Act
        List<UserDto> result = testObject.findAll();
        //Assert
        assertEquals(size, result.size());
    }

    @Test
    void findByLanguageTest(){
        //Arrange
        int size = 1;
        String language = "Java";
        //Act
        List<UserDto> result = testObject.findByLanguage(language);
        //Assert
        assertEquals(size, result.size());
    }

    @Test
    void findByLanguageExceptionTest(){
        //Arrange
        String language = ".Net";
        //Act
        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> testObject.findByLanguage(language));
        //Assert
        assertThat(e).hasMessageContaining("Could not find any users with language: " + language);
    }

    @Test
    void findByActiveTest(){
        //Arrange
        int size = 1;
        //Act
        List<UserDto> result = testObject.findAllByActive(true);
        //Assert
        assertEquals(size, result.size());
    }

    @Test
    void findByAvailableTest(){
        //Arrange
        int size = 1;
        //Act
        List<UserDto> result = testObject.findByAvailable(true);
        //Assert
        assertEquals(size, result.size());
    }

    @Test
    void findByLastActiveTest(){
        //Arrange
        int size = 2;
        LocalDate date = user1.getLastActive();
        //Act
        List<UserDto> result = testObject.findByLastActive(date);
        //Assert
        assertEquals(size, result.size());
    }

    @Test
    void CreateTest(){
        //Arrange
        int size = 3;
        List<Experience> experiences = new ArrayList<>();
        experiences.add(new Experience("Worked for 2 years."));
        List<Languages> languages = new ArrayList<>();
        languages.add(new Languages(".Net"));
        Address address3 = new Address("Road 3", "sweden" , "göteborg" , "21546");
        UserDto user;
        //Act
        testObject.create(new UserDto(null,"Kent", "Malmberg", "kent@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "Dstr1289!" , "user",
                "0704729432", "/image.jpg", 25, address3, new Qualifications(true, true, experiences, languages)));
        List<UserDto> result = testObject.findAll();
        //Assert
        assertEquals(size, result.size());
        for (UserDto dto: result) {
            user = dto;
            assertFalse(user.getUserId().isEmpty());
        }
    }

    @Test
    void updateAddressTest(){
        //Arrange
        UserDto updatedUser = converter.userToDto(user1);
        updatedUser.getContact().setCity("Växjö");
        //Act
        user1 = converter.dtoToUser(testObject.update(updatedUser));
        //Assert
        assertEquals(updatedUser.getContact(), user1.getContact());
    }

    @Test
    void updateAddLanguageTest(){
        //Arrange
        int expected = 2;
        UserDto updatedUser = converter.userToDto(user1);
        updatedUser.getQualifications().addLanguage(new Languages(".Net"));
        //Act
        user1 = converter.dtoToUser(testObject.update(updatedUser));
        //Assert
        assertEquals(expected, user1.getQualifications().getLanguage().size());
        user1.getQualifications().getLanguage().forEach(languages -> assertFalse(languages.getLanguagesId().isEmpty()));
    }

    @Test
    void deleteTest(){
        //Arrange
        int expected = 1;

        //Act
        testObject.delete(user2.getUserId());
        List<UserDto> result = testObject.findAll();
        //Assert
        assertEquals(expected, result.size());
    }

}*/
