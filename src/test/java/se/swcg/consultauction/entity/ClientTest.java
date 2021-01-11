package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.swcg.consultauction.dto.ClientDto;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class ClientTest {

    private Client test;


    @BeforeEach
    void setUp() {
        Date d = new Date();
        test = new Client("3", "CompName", "firstName", "Lname",
                "tob43@gmail.com", true,
                d, null, "0704129500", "qwertY1asdsd", "Client", "asddsagf", null);
    }



    @Test
    void testClient_created_successfully() {
        Date d = new Date();
        assertEquals("3", test.getClientId());
        assertEquals("CompName",test.getCompanyName());
        assertEquals("firstName", test.getFirstName());
        assertEquals("Lname", test.getLastName());
        assertEquals("0704129500",test.getPhoneNumber());
        assertEquals("tob43@gmail.com", test.getEmail());
        assertEquals("qwertY1asdsd", test.getPassword());
        assertEquals("Client", test.getRole());
        assertTrue(test.isActive());

    }

    @Test
    void test_hashCode_and_equals() {
        Date d = new Date();
        Client clientCopy = test;

        assertEquals(test, clientCopy);
        assertEquals(clientCopy.hashCode(), test.hashCode());
    }

    @Test
    public void test_toString_contains_correct_information() {
        String toString = test.toString();

        assertTrue(toString.contains("1"));
        assertTrue(toString.contains("firstName"));
        assertTrue(toString.contains("Lname"));
        assertTrue(toString.contains("tob43@gmail.com"));
        assertTrue(toString.contains("qwertY1asdsd"));
        assertTrue(toString.contains("Client"));
        assertTrue(toString.contains("true"));
    }

}
