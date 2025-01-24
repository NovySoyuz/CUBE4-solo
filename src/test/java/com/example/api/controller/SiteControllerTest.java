package com.example.api.controller;

import com.example.api.model.Services;
import com.example.api.model.Site;
import com.example.api.service.ServicesService;
import com.example.api.service.SiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SiteController.class)
class SiteControllerTest {
    // Permet d'éxécuter les requettes HTTP
    @Autowired
    private MockMvc mockMvc;
    // Simulation (mock) une dépendance injectée, on remplace la dépendance réelle par notre mock
    @MockBean
    private SiteService siteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createSiteTest() throws Exception {
        // Simulation d'une entité Services représentant la réponse attendue
        Site mockService = new Site();
        mockService.setId(1);
        mockService.setCity("Test du site");
        // Quand la methode createService est appelée avec n'importe quel parametre (any) retourne mockService
        // Sans cette etape c'est le service réel qui serait appelé, cela pourrait engendrer des erreurs
        Mockito.when(siteService.createSite(any())).thenReturn(mockService);
        // Simuler une requete POST
        mockMvc.perform(post("/api/site/")
                        //Corp de la requete est en JSON
                        .contentType(MediaType.APPLICATION_JSON)
                        // convertir l'objet mockService en JSON
                        .content(objectMapper.writeValueAsString(mockService)))
                // Verification des résultats
                .andExpect(status().isOk())
                //jsonPath permet de naviguer dans une structure JSON
                // Verification que l'attribut id retourne 1
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.city").value("Test du site"))
                .andExpect(status().isOk());
    }

    @Test
    void updateSiteTest() throws Exception {
        Site mockService = new Site();
        mockService.setId(1);
        mockService.setCity("update du site");
        // eq est un matcher, il verifie que le premier paramétre est strictement égal à 1
        Mockito.when(siteService.updateSite(eq(1),any())).thenReturn(mockService);
        mockMvc.perform(put("/api/site/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockService)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.city").value("update du site"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllSiteTest() throws Exception {
        Site mockService = new Site();
        mockService.setId(1);
        mockService.setCity("get du site");

        Mockito.when(siteService.getAllSite()).thenReturn(Collections.singletonList(mockService));
        mockMvc.perform(get("/api/site/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].city").value("get du site"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSiteTest() throws Exception{
        Mockito.doNothing().when(siteService).deleteSite(1);

        mockMvc.perform(delete("/api/site/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Suppression success"));
    }
}