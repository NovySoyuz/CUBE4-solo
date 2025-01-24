package com.example.api.service;

import com.example.api.model.Services;
import com.example.api.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
class ServicesServiceTest {
    @MockBean
    private ServiceRepository serviceRepository;
    @Autowired
    private ServicesService servicesService;
    Services mockServices = new Services();

    @Test
    void updateServices() {
        mockServices.setName("test");
        mockServices.setId(1);
        // La methode findById retourne un optional
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(mockServices));

        // Simulation du comportement de save
        // Quand on appelle la methode save avec un objet de type Services
        Mockito.when(serviceRepository.save(Mockito.any(Services.class)))
                // expression lambda, permet de retourner le premier argument passé à save
        .thenAnswer(firstArgument -> firstArgument.getArgument(0));

        Services updateServices = servicesService.updateServices(1, "new test");

        assertNotNull(updateServices);
        assertEquals(1, updateServices.getId());
        assertEquals("new test", updateServices.getName());


    }

    @Test
    void getAllServices() {
        mockServices.setName("test");
        mockServices.setId(1);

        Mockito.when(serviceRepository.findAll()).thenReturn(Collections.singletonList(mockServices));
        Services getAllServices = (Services) servicesService.getAllServices();

        assertNotNull(getAllServices);
    }

    @Test
    void deleteService() {
    }
}