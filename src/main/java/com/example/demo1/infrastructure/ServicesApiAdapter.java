package com.example.demo1.infrastructure;

import com.example.demo1.domain.model.Services;
import com.example.demo1.domain.model.Site;
import com.example.demo1.domain.ports.ServicesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ServicesApiAdapter implements ServicesRepository {
    private final String apiURL = "http://localhost:8080/api/services/search";
    @Override
    public List<Services> getAllServices() {
        List<Services> services;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            ObjectMapper mapper = new ObjectMapper();
            services = mapper.readValue(connection.getInputStream(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Services.class));

        } catch (Exception e) {
            throw new RuntimeException("No services found" +e);
        }
        return services;
    }
}
