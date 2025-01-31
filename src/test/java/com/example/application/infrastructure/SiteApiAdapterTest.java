package com.example.application.infrastructure;

import com.example.application.domain.model.Site;
import com.example.application.utils.HttpApiAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SiteApiAdapterTest {

    private HttpApiAdapter httpApiAdapterMock;
    private SiteApiAdapter siteApiAdapter;

    @BeforeEach
    void setUp() {
        httpApiAdapterMock = Mockito.mock(HttpApiAdapter.class);
        siteApiAdapter = new SiteApiAdapter(httpApiAdapterMock);
    }

    @Test
    void testGetAllSite() {
        Site site1 = new Site();
        site1.setCity("Paris");
        site1.setId(1);

        Site site2 = new Site();
        site2.setCity("Montpellier");
        site2.setId(2);
        List<Site> expectedSites = Arrays.asList(site1, site2);

        // Simulation du comportement de httpApiAdapter
        Mockito.when(httpApiAdapterMock.sendGetAllRequest(Mockito.anyString(), Mockito.eq(Site.class)))
                .thenReturn(expectedSites);

        // Appel de la méthode testée
        List<Site> actualSites = siteApiAdapter.getAllSite();

        // Vérification du résultat
        assertEquals(expectedSites, actualSites);

        // Vérification que la méthode a bien été appelée avec l'URL correcte
        Mockito.verify(httpApiAdapterMock).sendGetAllRequest(Mockito.anyString(), Mockito.eq(Site.class));
    }

    @Test
    void testCreateSite() {
        Site newSite = new Site();
        newSite.setCity("Paris");
        newSite.setId(1);

        Mockito.when(httpApiAdapterMock.sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                        Mockito.eq(newSite), Mockito.eq(Site.class)))
                .thenReturn(newSite);

        Site createdSite = siteApiAdapter.createSite(newSite);

        assertEquals(newSite, createdSite);
        Mockito.verify(httpApiAdapterMock).sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                Mockito.eq(newSite), Mockito.eq(Site.class));
    }

    @Test
    void testUpdateSite() {
        Site updatedSite = new Site();
        updatedSite.setCity("Paris");
        updatedSite.setId(1);

        Mockito.when(httpApiAdapterMock.sendDataRequest(Mockito.anyString(), Mockito.eq("PUT"),
                        Mockito.eq(updatedSite), Mockito.eq(Site.class)))
                .thenReturn(updatedSite);

        Site result = siteApiAdapter.updateSite(updatedSite);

        assertEquals(updatedSite, result);
        Mockito.verify(httpApiAdapterMock).sendDataRequest(Mockito.anyString(), Mockito.eq("PUT"),
                Mockito.eq(updatedSite), Mockito.eq(Site.class));
    }

    @Test
    void testDeleteSite() {
        int siteId = 2;

        Mockito.when(httpApiAdapterMock.deleteMethodById(Mockito.anyString())).thenReturn(true);

        boolean result = siteApiAdapter.deleteSite(siteId);

        assertTrue(result);
        Mockito.verify(httpApiAdapterMock).deleteMethodById(Mockito.anyString());
    }
}