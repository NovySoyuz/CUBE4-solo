package com.example.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicesTest {

    private Services services;

    // Initialisation de l'objet Services avant chaque test
    @BeforeEach
    void setUp() {
        services = new Services();
    }

    @Test
    void testSetAndGetId() {
        int expectedId = 1;

        services.setId(expectedId);
        int actualId = services.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetAndGetName() {
        String expectedName = "Test Service";

        services.setName(expectedName);
        String actualName = services.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void testToString() {
        String expectedName = "Test Service";

        services.setName(expectedName);
        String actualToString = services.toString();

        assertEquals(expectedName, actualToString);
    }
}