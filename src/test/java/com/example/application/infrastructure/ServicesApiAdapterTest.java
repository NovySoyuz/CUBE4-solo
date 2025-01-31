package com.example.application.infrastructure;

import com.example.application.domain.model.Services;
import com.example.application.utils.HttpApiAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class ServicesApiAdapterTest {
    private HttpApiAdapter httpApiAdapterMock;
    private ServicesApiAdapter servicesApiAdapter;

    @BeforeEach
    void setUp() {
        // Création d'un mock de HttpApiAdapter
        httpApiAdapterMock = Mockito.mock(HttpApiAdapter.class);

        // Création d'une instance de ServicesApiAdapter en injectant le mock
        servicesApiAdapter = new ServicesApiAdapter(httpApiAdapterMock);
    }

    @Test
    void getAllServices() {
        Services service1 = new Services();
        service1.setName("DSI");
        service1.setId(1);

        Services service2 = new Services();
        service2.setName("RH");
        service2.setId(2);

        List<Services> expectedServices = Arrays.asList(service1, service2);

        // Simulation du comportement de httpApiAdapter
        Mockito.when(httpApiAdapterMock.sendGetAllRequest(anyString(), eq(Services.class)))
                .thenReturn(expectedServices);

        // Appel de la méthode testée
        List<Services> actualServices = servicesApiAdapter.getAllServices();

        // Vérification du résultat
        assertEquals(expectedServices, actualServices);

        // Vérification que la méthode a bien été appelée avec l'URL correcte
        verify(httpApiAdapterMock).sendGetAllRequest(eq("http://localhost:8080/api/services/search"), eq(Services.class));
    }

    @Test
    void createService() {
        Services newService = new Services();
        newService.setId(1);
        newService.setName("RH");

        Mockito.when(httpApiAdapterMock.sendDataRequest(anyString(), eq("POST"), eq(newService), eq(Services.class)))
                .thenReturn(newService);

        Services createdService = servicesApiAdapter.createService(newService);

        assertEquals(newService, createdService);

        verify(httpApiAdapterMock).sendDataRequest(eq("http://localhost:8080/api/services/"), eq("POST"), eq(newService), eq(Services.class));
    }

    @Test
    void updateService() {
        Services updatedService = new Services();
        updatedService.setId(1);
        updatedService.setName("update");

        Mockito.when(httpApiAdapterMock.sendDataRequest(anyString(), eq("PUT"), eq(updatedService), eq(Services.class)))
                .thenReturn(updatedService);

        Services result = servicesApiAdapter.updateService(updatedService);

        assertEquals(updatedService, result);

        verify(httpApiAdapterMock).sendDataRequest(eq("http://localhost:8080/api/services/1"), eq("PUT"), eq(updatedService), eq(Services.class));
    }

    @Test
    void deleteService() {
        int serviceId = 1;

        Mockito.when(httpApiAdapterMock.deleteMethodById(anyString())).thenReturn(true);

        boolean isDeleted = servicesApiAdapter.deleteService(serviceId);

        assertTrue(isDeleted);

        verify(httpApiAdapterMock).deleteMethodById(eq("http://localhost:8080/api/services/1"));
    }
}