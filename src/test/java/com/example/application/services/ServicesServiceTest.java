package com.example.application.services;

import com.example.application.domain.model.Services;
import com.example.application.domain.ports.ServicesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ServicesServiceTest {

    private ServicesRepository servicesRepositoryMock;
    private ServicesService servicesService;

    @BeforeEach
    void setUp() {
        // Mock du repository
        servicesRepositoryMock = Mockito.mock(ServicesRepository.class);
        // Service utilisant le mock
        servicesService = new ServicesService(servicesRepositoryMock);
    }

    @Test
    void testGetAllServices() {
        Services service1 = new Services();
        service1.setId(1);
        service1.setName("Service 1");

        Services service2 = new Services();
        service2.setId(2);
        service2.setName("Service 2");

        List<Services> expectedServices = Arrays.asList(service1, service2);

        // Quand on appelle getAllServices, il doit retourner nos deux services
        Mockito.when(servicesRepositoryMock.getAllServices()).thenReturn(expectedServices);

        // Appel de la vraie méthode
        List<Services> actualServices = servicesService.getAllServices();

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(expectedServices, actualServices);

        // Vérifie que la méthode mockée est appelée
        verify(servicesRepositoryMock).getAllServices();
    }

    @Test
    void testCreateService() {
        Services newService = new Services();
        newService.setName("New Service");

        Services expectedService = new Services();
        expectedService.setId(1);
        expectedService.setName("New Service");

        // Quand on appelle createService, il doit retourner le service attendu
        Mockito.when(servicesRepositoryMock.createService(newService)).thenReturn(expectedService);

        // Appel de la vraie méthode
        Services createdService = servicesService.createService(newService);

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(expectedService, createdService);

        // Vérifie que la méthode mockée est appelée
        verify(servicesRepositoryMock).createService(newService);
    }

    @Test
    void testUpdateService() {
        Services updatedService = new Services();
        updatedService.setId(1);
        updatedService.setName("Updated Service");

        // Quand on appelle updateService, il doit retourner le service mis à jour
        Mockito.when(servicesRepositoryMock.updateService(updatedService)).thenReturn(updatedService);

        // Appel de la vraie méthode
        Services result = servicesService.updateService(updatedService);

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(updatedService, result);

        // Vérifie que la méthode mockée est appelée
        verify(servicesRepositoryMock).updateService(updatedService);
    }

    @Test
    void testDeleteService() {
        int serviceId = 1;

        // Quand on appelle deleteService, il doit retourner true
        Mockito.when(servicesRepositoryMock.deleteService(serviceId)).thenReturn(true);

        // Appel de la vraie méthode
        boolean isDeleted = servicesService.deleteService(serviceId);

        // Vérifie que le résultat est bien true
        assertEquals(true, isDeleted);

        // Vérifie que la méthode mockée est appelée
        verify(servicesRepositoryMock).deleteService(serviceId);
    }
}