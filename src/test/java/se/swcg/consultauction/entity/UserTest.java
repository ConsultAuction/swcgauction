package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {
    User testobject;

    @BeforeEach
    void setUp(){
        testobject = new User( "id1" , "Robin", "Sandberg", "Robin@mail.com", true, LocalDate.now(), LocalDate.now(), true , "1234" , "user" , "0147852", "image1", 50, null);
    }

    @Test
    void getTest(){
       User newUser = new User(testobject.getUserId(), testobject.getFirstName(), testobject.getLastName(), testobject.getEmail(),
               testobject.isActive() , testobject.getDateOfSignUp(), testobject.getLastActive(), testobject.isAvailable(), testobject.getPassword(), testobject.getRole(),
               testobject.getPhoneNumber(), testobject.getImage(), testobject.getMinPrice(), testobject.getQualifications());

        assertEquals(newUser , testobject);
    }

}