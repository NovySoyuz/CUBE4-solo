package com.example.demo1.infrastructure;

import com.example.demo1.domain.model.Site;
import com.example.demo1.domain.ports.SiteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SiteApiAdapter implements SiteRepository {
    private final String apiURL = "http://localhost:8080/api/site/search";
    @Override
    public List<Site> getAllSite() {
        List<Site> sites;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            ObjectMapper mapper = new ObjectMapper();
            sites = mapper.readValue(connection.getInputStream(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Site.class));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des sites", e);
        }
        return sites;
    }
}
