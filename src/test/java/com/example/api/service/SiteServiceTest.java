package com.example.api.service;

import com.example.api.model.Site;
import com.example.api.repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SiteServiceTest {

    @MockBean
    private SiteRepository siteRepository;

    @Autowired
    private SiteService siteService;

    Site mockSite = new Site();

    @Test
    void createSite() {
        mockSite.setCity("Paris");

        // Simulation du comportement de save
        Mockito.when(siteRepository.save(Mockito.any(Site.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Site createdSite = siteService.createSite("Paris");

        assertNotNull(createdSite);
        assertEquals("Paris", createdSite.getCity());
        Mockito.verify(siteRepository, Mockito.times(1)).save(Mockito.any(Site.class));
    }

    @Test
    void updateSite() {
        mockSite.setId(1);
        mockSite.setCity("Paris");

        // Simulation de la recherche du site par ID
        Mockito.when(siteRepository.findById(1)).thenReturn(Optional.of(mockSite));

        // Simulation du comportement de save
        Mockito.when(siteRepository.save(Mockito.any(Site.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Site updatedSite = siteService.updateSite(1, "Lyon");

        assertNotNull(updatedSite);
        assertEquals(1, updatedSite.getId());
        assertEquals("Lyon", updatedSite.getCity());
        Mockito.verify(siteRepository, Mockito.times(1)).findById(1);
        Mockito.verify(siteRepository, Mockito.times(1)).save(mockSite);
    }
    @Test
    void getAllSites() {
        mockSite.setId(1);
        mockSite.setCity("Paris");

        Mockito.when(siteRepository.findAll()).thenReturn(Collections.singletonList(mockSite));

        Iterable<Site> allSites = siteService.getAllSite();
        // Conversion de Iterable en List<Services>
        // Iterable ne permet pas d'acceder directement au element via get(index)
        // StreamSupport.stream(allServices.spliterator(), false) convertit Iterable en un flux (Stream).
        List<Site> siteList = StreamSupport.stream(allSites.spliterator(), false)
                // // transforme le flux en list
                .collect(Collectors.toList());

        assertNotNull(siteList);
        assertFalse(siteList.isEmpty());
        assertEquals(1, siteList.get(0).getId());
        assertEquals("Paris", siteList.get(0).getCity());
    }

    @Test
    void deleteSite() {
        mockSite.setId(1);
        mockSite.setCity("Paris");

        Mockito.when(siteRepository.findById(1)).thenReturn(Optional.of(mockSite));

        siteService.deleteSite(1);

        Mockito.verify(siteRepository, Mockito.times(1)).findById(1);
        Mockito.verify(siteRepository, Mockito.times(1)).delete(mockSite);
    }

}