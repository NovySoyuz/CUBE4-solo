package com.example.application.infrastructure;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;
import com.example.application.utils.HttpApiAdapter;

import java.util.List;

public class SiteApiAdapter implements SiteRepository {
    private final HttpApiAdapter httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/site/";
    // Ajout d'un constructeur pour utiliser dans les classes de tests
    // Permet l'injection de httpApiAdapter
    public SiteApiAdapter(HttpApiAdapter httpApiAdapter) {
        this.httpApiAdapter = httpApiAdapter;
    }
    // Lors de la création d'un ServiceApiAdapter il utilise toujours une instance de httpApiAdapter
    public SiteApiAdapter () {
        this.httpApiAdapter = new HttpApiAdapter();
    }

    @Override
    public List<Site> getAllSite() {
        return httpApiAdapter.sendGetAllRequest(apiURL+"search", Site.class);
    }

    @Override
    public Site createSite(Site site) {
        return httpApiAdapter.sendDataRequest(apiURL, "POST", site, Site.class);
    }

    @Override
    public Site updateSite(Site site) {
        return httpApiAdapter.sendDataRequest(apiURL + site.getId(), "PUT", site, Site.class);
    }

    @Override
    public boolean deleteSite(int id) {
        return httpApiAdapter.deleteMethodById(apiURL + id);
    }

}
