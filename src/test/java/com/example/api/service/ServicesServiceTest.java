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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        // Retourne une liste contenant uniquement mockService
        Mockito.when(serviceRepository.findAll()).thenReturn(Collections.singletonList(mockServices));
        // Appel de la methode getAllServices
        Iterable<Services> allServices = servicesService.getAllServices();
        // Conversion de Iterable en List<Services>
        // Iterable ne permet pas d'acceder directement au element via get(index)
        // StreamSupport.stream(allServices.spliterator(), false) convertit Iterable en un flux (Stream).
        List<Services> servicesList = StreamSupport.stream(allServices.spliterator(), false)
                // transforme le flux en list
                .collect(Collectors.toList());

        assertNotNull(servicesList);
        assertFalse(servicesList.isEmpty());
        assertEquals(1, servicesList.get(0).getId());
        assertEquals("test", servicesList.get(0).getName());
    }

    @Test
    void createServices() {
        mockServices.setName("test service");

        // Simulation du comportement de save
        Mockito.when(serviceRepository.save(Mockito.any(Services.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Services createdService = servicesService.createServices("test service");

        assertNotNull(createdService);
        assertEquals("test service", createdService.getName());
        Mockito.verify(serviceRepository, Mockito.times(1)).save(Mockito.any(Services.class));
    }

    @Test
    void deleteService() {
        mockServices.setId(1);
        mockServices.setName("test service");

        // Simulation du comportement findById qui retourne un Optional contenant l'objet
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(mockServices));

        // Suppression du service
        servicesService.deleteService(1);

        // Vérification que la méthode delete a bien été appelée une seule fois
        Mockito.verify(serviceRepository, Mockito.times(1)).findById(1);
        Mockito.verify(serviceRepository, Mockito.times(1)).delete(mockServices);
    }
}