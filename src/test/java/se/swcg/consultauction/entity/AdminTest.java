package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private Admin testAdmin;

    @BeforeEach
    void setUp() {
        testAdmin = new Admin("1", "firstname", "lastname", "a@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

    }

    @Test
    void testAdmin_created_successfully() {
        assertEquals("1", testAdmin.getAdminId());
        assertEquals("firstname", testAdmin.getFirstName());
        assertEquals("lastname", testAdmin.getLastName());
        assertEquals("a@l.com", testAdmin.getEmail());
        assertEquals("password", testAdmin.getPassword());
        assertEquals("Admin", testAdmin.getRole());
        assertTrue(testAdmin.isActive());
        assertEquals(LocalDate.of(2020, 12, 23), testAdmin.getLastActive());
    }

    @Test
    void test_hashCode_and_equals() {
        Admin adminCopy = new Admin("1", "firstname", "lastname", "a@l.com",
                "password", "Admin", true,
                LocalDate.of(2020, 12, 23));

        assertTrue(adminCopy.equals(testAdmin));
        assertEquals(adminCopy.hashCode(), testAdmin.hashCode());
    }

    @Test
    public void test_toString_contains_correct_information() {
        String toString = testAdmin.toString();

        assertTrue(toString.contains("1"));
        assertTrue(toString.contains("firstname"));
        assertTrue(toString.contains("lastname"));
        assertTrue(toString.contains("a@l.com"));
        assertTrue(toString.contains("password"));
        assertTrue(toString.contains("Admin"));
        assertTrue(toString.contains("true"));
        assertTrue(toString.contains("2020-12-23"));
    }
}