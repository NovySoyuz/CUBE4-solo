package com.example.api.controller;

import com.example.api.model.Services;
import com.example.api.service.ServicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Configure uniquement la couche controller sans charger l'ensemble du contexte Spring Boot
@WebMvcTest(ServicesController.class)
public class ServicesControllerTest {
    // Permet d'éxécuter les requettes HTTP
    @Autowired
    private MockMvc mockMvc;
    // Simulation (mock) une dépendance injectée, on remplace la dépendance réelle par notre mock, utile pour spring
    @MockBean
    private ServicesService servicesService;
    @Autowired
    private ObjectMapper objectMapper;
    Services mockService = new Services();
    @Test
    void createServiceTest() throws Exception {
        // Simulation d'une entité Services représentant la réponse attendue
        mockService.setId(1);
        mockService.setName("Test du service");
        // Quand la methode createService est appelée avec n'importe quel parametre (any) retourne mockService
        // Sans cette etape c'est le service réel qui serait appelé, cela pourrait engendrer des erreurs
        Mockito.when(servicesService.createServices(any())).thenReturn(mockService);
        // Simuler une requete POST
        mockMvc.perform(post("/api/services/")
                //Corp de la requete est en JSON
                .contentType(MediaType.APPLICATION_JSON)
                // convertir l'objet mockService en JSON
                .content(objectMapper.writeValueAsString(mockService)))
                // Verification des résultats
                .andExpect(status().isOk())
                //jsonPath permet de naviguer dans une structure JSON
                // Verification que l'attribut id retourne 1
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test du service"))
                .andExpect(status().isOk());
    }

    @Test
    void updateServiceTest() throws Exception {
        mockService.setId(1);
        mockService.setName("update du Service");
        // eq est un matcher, il verifie que le premier paramétre est strictement égal à 1
        Mockito.when(servicesService.updateServices(eq(1),any())).thenReturn(mockService);
        mockMvc.perform(put("/api/services/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockService)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("update du Service"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllServicesTest() throws Exception {
        mockService.setId(1);
        mockService.setName("get du Service");
        // Collections.singletonList(mockService) doit retourner uniquement une seule liste
        Mockito.when(servicesService.getAllServices()).thenReturn(Collections.singletonList(mockService));
        mockMvc.perform(get("/api/services/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("get du Service"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteServiceTest() throws Exception{
        Mockito.doNothing().when(servicesService).deleteService(1);
        mockMvc.perform(delete("/api/services/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Suppress success"));
    }
}
