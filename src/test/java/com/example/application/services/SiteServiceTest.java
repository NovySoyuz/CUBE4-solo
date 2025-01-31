package com.example.application.services;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class SiteServiceTest {

    private SiteRepository siteRepositoryMock;
    private SiteService siteService;

    @BeforeEach
    void setUp() {
        // Mock du repository
        siteRepositoryMock = Mockito.mock(SiteRepository.class);
        // Service utilisant le mock
        siteService = new SiteService(siteRepositoryMock);
    }

    @Test
    void testGetAllSite() {
        Site site1 = new Site();
        site1.setId(1);
        site1.setCity("Montpellier");

        Site site2 = new Site();
        site2.setId(2);
        site2.setCity("Nîmes");

        List<Site> expectedSites = Arrays.asList(site1, site2);

        // Quand on appelle getAllSite, il doit retourner nos deux sites
        Mockito.when(siteRepositoryMock.getAllSite()).thenReturn(expectedSites);

        // Appel de la vraie méthode
        List<Site> actualSites = siteService.getAllSite();

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(expectedSites, actualSites);

        // Vérifie que la méthode mockée est appelée
        verify(siteRepositoryMock).getAllSite();
    }

    @Test
    void testCreateSite() {
        Site newSite = new Site();
        newSite.setCity("Montpellier");

        Site expectedSite = new Site();
        expectedSite.setId(1);
        expectedSite.setCity("Montpellier");

        // Quand on appelle createSite, il doit retourner le site attendu
        Mockito.when(siteRepositoryMock.createSite(newSite)).thenReturn(expectedSite);

        // Appel de la vraie méthode
        Site createdSite = siteService.createSite(newSite);

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(expectedSite, createdSite);

        // Vérifie que la méthode mockée est appelée
        verify(siteRepositoryMock).createSite(newSite);
    }

    @Test
    void testUpdateSite() {
        Site updatedSite = new Site();
        updatedSite.setId(1);
        updatedSite.setCity("Updated City");

        // Quand on appelle updateSite, il doit retourner le site mis à jour
        Mockito.when(siteRepositoryMock.updateSite(updatedSite)).thenReturn(updatedSite);

        // Appel de la vraie méthode
        Site result = siteService.updateSite(updatedSite);

        // Comparaison du résultat réel et du résultat attendu
        assertEquals(updatedSite, result);

        // Vérifie que la méthode mockée est appelée
        verify(siteRepositoryMock).updateSite(updatedSite);
    }

    @Test
    void testDeleteSite() {
        int siteId = 1;

        // Quand on appelle deleteSite, il doit retourner true
        Mockito.when(siteRepositoryMock.deleteSite(siteId)).thenReturn(true);

        // Appel de la vraie méthode
        boolean isDeleted = siteService.deleteSite(siteId);

        // Vérifie que le résultat est bien true
        assertEquals(true, isDeleted);

        // Vérifie que la méthode mockée est appelée
        verify(siteRepositoryMock).deleteSite(siteId);
    }
}