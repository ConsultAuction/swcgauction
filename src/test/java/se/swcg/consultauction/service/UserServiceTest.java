package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.Experience;
import se.swcg.consultauction.entity.Languages;
import se.swcg.consultauction.entity.Qualifications;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class UserServiceTest {

    @Autowired
    UserService testObject;
    @Autowired
    UserRepository repo;


    User user1;
    User user2;
    Languages languages;
    Qualifications qualifications;
    Experience experience;
    List<Experience> experienceList;
    List<Languages> languagesList;

    @BeforeEach
    void setUp() {
        languages = new Languages("java");
        languagesList.add(languages);
        experience = new Experience("Worked on nothing");
        experienceList.add(experience);
        qualifications = new Qualifications(false, true, experienceList,languagesList);
        user1 = new User("Robin", "sandberg", "robin@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD" , "user",
                "0704729300", "/image.jpg", 25, null, qualifications);
        user1 = repo.save(user1);
        user2 = new User("Sten" , "Stensson", "sten@hotmail.com" , false, LocalDate.now(), LocalDate.now(), false, "1234Dcsa" , "user",
                "0704139500", "/image.jpg", 25, null, null);
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
        String language = "Java";
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
        //Act
        testObject.create(new UserForm("Kent", "Malmberg", "kent@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "Dstr1289" , "user",
                "0704729432", "/image.jpg", 25, null, null));
        List<UserDto> result = testObject.findAll();
        UserDto user;
        //Assert
        assertEquals(size, result.size());
        for (UserDto dto: result) {
            user = dto;
            assertFalse(user.getUserId().isEmpty());
        }
    }

    @Test
    void updateTest(){

    }

}