package se.swcg.consultauction.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.entity.Client;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
 class ClientRepositoryTest {


    @Autowired
    private ClientRepository repo;
    private Client test;

    @BeforeEach
    void setUp() {
        Date d = new Date();
        test = new Client("3", "CompName", "firstName", "Lname",
                "tob43@gmail.com", true,
                d, d, "0704129500", "qwertY1asdsd", "Client", "asddsagf", null);

        test = repo.save(test);
    }

    @Test
    void given_findAll_should_return_list_size_of_1() {
        int expectedSize = 1;

        List<Client> result = repo.findAll();

        assertEquals(expectedSize, result.size());
    }

    @Test
    void given_findByEmail_should_return_optional_of_adminEmail() {
        String email = test.getEmail();

        Optional<Client> result = repo.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

    }

    @Test
    void given_findByCompanyNameIgnoreCase_should_return_list_size_of_1(){

        int exp = 1;
        String cName = test.getCompanyName();

        List<Client> res = repo.findByCompanyNameIgnoreCase(cName);

        assertEquals(exp,res.size());

    }


}
