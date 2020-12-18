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
    Address testAddress;
    Qualifications testQualifications;
    Experience testExperience;
    Languages testLanguage;


    @BeforeEach
    void setUp() {
        List<Experience> experienceList = new ArrayList<>();
        List<Languages> languages = new ArrayList<>();
        testExperience = new Experience("expId4", "Worked for everyone.");
        testLanguage = new Languages("langId3", "Java");
        experienceList.add(testExperience);
        languages.add(testLanguage);
        testAddress = new Address("addressId1", "road 1", "sweden" , "stockholm" , "08");
        testQualifications = new Qualifications("qId2", false , true , experienceList , languages);
        testObject = new User("Robin" , "sandberg", "robin@mail.com" , true, LocalDate.now(), LocalDate.now(), true, "1234" , "user",
                "0147852369", "/image.jpg", 25, testAddress, testQualifications);
        testObject = repo.save(testObject);

    }

    /*Collection<User> findAll();
    Collection<User> findByQualificationContainingLanguageIgnoreCase(String Language);
    Collection<User> findByLastActive(LocalDate lastActive);
    Collection<User> findAllByActive(boolean active);
    Collection<User> findByAvailable(boolean available);*/

    @Test
    void testFindByQualificationContainingLanguageIgnoreCase(){

    }

}