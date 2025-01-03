package com.example.application.infrastructure;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;

import java.util.List;

public class SiteApiAdapter implements SiteRepository {
    private final HttpApiAdapter httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/site/";
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
