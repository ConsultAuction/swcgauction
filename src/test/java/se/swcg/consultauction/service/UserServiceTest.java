package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.dto.UserForm;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @TestConfiguration
    public static class UserServiceTestConfig{

        @Bean
        public UserService userService(UserRepository repo, DtoConversionService converter){
            return new UserServiceImpl(repo,converter);
        }
    }

    @Autowired
    UserService userService;
    @MockBean
            UserRepository repoMock;
    @MockBean
            DtoConversionService converterMock;

    User newUser;
    UserDto testObject;
    UserDto testObject2;
    UserForm testForm;
    UserForm testForm2;
   /* Address testAddress;
    Qualifications testQualifications;
    Experience testExperience;
    Languages testLanguage;*/

    @BeforeEach
    void setUp() {
       /* List<Experience> experienceList = new ArrayList<>();
        List<Languages> languages = new ArrayList<>();
        testExperience = new Experience("expId4", "Worked for everyone.");
        testLanguage = new Languages("langId3", "Java");
        experienceList.add(testExperience);
        languages.add(testLanguage);
        testAddress = new Address("addressId1", "road 1", "sweden" , "stockholm" , "08");
        testQualifications = new Qualifications("qId2", false , true , experienceList , languages);*/

        testForm = new UserForm("Robin" , "sandberg", "robin@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234abcD" , "user",
                "0704729300", "/image.jpg", 25, null, null);
        testObject = userService.create(testForm);
        //newUser = repoMock.save(converterMock.userFormToUser(testForm));
        //testObject = converterMock.userToDto(newUser);
        testForm2 = new UserForm("Sten" , "Stensson", "sten@hotmail.com" , false, LocalDate.now(), LocalDate.now(), false, "1234Dcsa" , "user",
                "0704139500", "/image.jpg", 25, null, null);
        //testObject2 = converterMock.userToDto(repoMock.save(converterMock.userFormToUser(testForm2)));
        testObject2 = userService.create(testForm2);
        System.out.println(userService.findAll());

    }

    @Test
    void testFindById(){
        //Arrange
        //String id = testObject.getUserId();
        int expected = 2;
        List<UserDto> foundUsers;

        //Act
        //UserDto foundUser = userService.findById(id);
        foundUsers = userService.findAll();

        //Assert
       // assertEquals(testObject.getEmail(), foundUser.getEmail());
        assertEquals(expected, foundUsers.size());
    }

}