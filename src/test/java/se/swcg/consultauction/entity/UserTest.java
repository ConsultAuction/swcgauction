package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {
    User testobject;

    @BeforeEach
    void setUp(){
        //testobject = new User("Robin", "Sandberg", "Robin@mail.com", true, LocalDate.now().minusDays(), LocalDate.now())
    }


}