package com.example.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiteTest {
    private Site site;

    // Initialisation de l'objet Site avant chaque test
    @BeforeEach
    void setUp() {
        site = new Site();
    }

    @Test
    void testSetAndGetId() {
        int expectedId = 1;

        site.setId(expectedId);
        int actualId = site.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetAndGetCity() {
        String expectedCity = "Montpellier";

        site.setCity(expectedCity);
        String actualCity = site.getCity();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    void testToString() {
        String expectedCity = "Montpellier";

        site.setCity(expectedCity);
        String actualToString = site.toString();

        assertEquals(expectedCity, actualToString);
    }
}