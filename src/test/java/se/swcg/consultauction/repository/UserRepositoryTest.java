package se.swcg.consultauction.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.swcg.consultauction.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    User testObject;
    User testObject2;
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
        testQualifications = new Qualifications("qId2", false , true , experienceList , languages);
        */
        testObject = new User("Robin" , "sandberg", "robin@hotmail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234Adve" , "user",
                "0147852369", "/image.jpg", 25, null, null);
        testObject = repo.save(testObject);
        testObject2 = new User("Sten" , "Stensson", "sten@hotmail.com" , false, LocalDate.now(), LocalDate.now(), false, "1234Abcd" , "user",
                "0147852369", "/image.jpg", 25, null, null);
        testObject2 = repo.save(testObject2);

    }


    /*Got to fix address and qualifications (probably repository stuff before able to run this test).
    @Test
    void testFindByQualificationsLanguageContainingIgnoreCase(){
        //Arrange
        String search = "jaVa";

        //Act
        List<User> foundUsers = repo.findByQualificationsLanguageContainingIgnoreCase(search);

        //Assert
        assertNotNull(foundUsers);

    }*/

    @Test
    void testFindByLastActive(){
        //Arrange
        List<User> foundUsers;
        LocalDate check = testObject.getLastActive();
        int expected = 2;
        //Act
        foundUsers = repo.findByLastActive(check);
        //Assert
        assertEquals(expected, foundUsers.size());
    }

    @Test
    void testFindAllByActive(){
        //Arrange
        boolean check = testObject.isActive();
        List<User> foundUsers;
        int expected = 1;
        //Act
        foundUsers = repo.findAllByActive(check);
        //Assert
        assertEquals(expected, foundUsers.size());
    }

    @Test
    void testFindByAvailable(){
        //Arrange
        boolean check = testObject.isAvailable();
        List<User> foundUsers;
        int expected = 1;
        //Act
        foundUsers = repo.findByAvailable(check);
        //Assert
        assertEquals(expected, foundUsers.size());
    }

    @Test
    void testFindAll(){
        //Arrange
        List<User> foundUsers;
        int expected = 2;
        //Act
        foundUsers = repo.findAll();
        //Assert
        assertEquals(expected, foundUsers.size());
    }
}