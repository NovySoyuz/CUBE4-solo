package com.example.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;

    // Initialisation de l'objet Employee avant chaque test
    @BeforeEach
    void setUp () {
        employee = new Employee();
    }

    @Test
    void testSetAndGetName() {
        String expectedName = "toto";

        employee.setName(expectedName);
        String actualName = employee.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void testSetAndGetMail() {
        String expectedMail = "toto@toto.com";

        employee.setMail(expectedMail);
        String actualMail = employee.getMail();

        assertEquals(expectedMail, actualMail);
    }

    @Test
    void testSetAndGetPhone() {
        String expectedPhone = "1234567890";

        employee.setPhone(expectedPhone);
        String actualPhone = employee.getPhone();

        assertEquals(expectedPhone, actualPhone);
    }

    @Test
    void testSetAndGetIsAdmin() {
        int expectedIsAdmin = 1;

        employee.setIs_admin(expectedIsAdmin);
        int actualIsAdmin = employee.getIs_admin();

        assertEquals(expectedIsAdmin, actualIsAdmin);
    }

    @Test
    void testSetAndGetServices() {
        Services expectedServices = new Services();
        expectedServices.setName("test");

        employee.setServices(expectedServices);
        Services actualService = employee.getServices();

        assertEquals(expectedServices, actualService);
        assertEquals("test", actualService.getName());
    }

    @Test
    void testSetAndGetSite() {
        Site expectedSite = new Site();
        expectedSite.setCity("Montpellier");

        employee.setSite(expectedSite);
        Site actualSite = employee.getSite();

        assertEquals(expectedSite, actualSite);
        assertEquals("Montpellier", actualSite.getCity());
    }


}