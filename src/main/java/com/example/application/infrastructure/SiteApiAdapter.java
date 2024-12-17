package com.example.application.infrastructure;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;

import java.util.List;

public class SiteApiAdapter implements SiteRepository {
    private final HttpApiAdapter httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/site/search";
    public SiteApiAdapter () {
        this.httpApiAdapter = new HttpApiAdapter();
    }
    @Override
    public List<Site> getAllSite() {
        return httpApiAdapter.sendGetAllRequest(apiURL, Site.class);
    }
}
