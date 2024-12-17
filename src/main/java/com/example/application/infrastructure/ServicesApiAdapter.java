package com.example.application.infrastructure;

import com.example.application.domain.model.Services;
import com.example.application.domain.ports.ServicesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ServicesApiAdapter implements ServicesRepository {
    private final HttpApiAdapter  httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/services/search";
    public ServicesApiAdapter() {
        this.httpApiAdapter = new HttpApiAdapter();
    }
    @Override
    public List<Services> getAllServices() {
        return httpApiAdapter.sendGetAllRequest(apiURL, Services.class);
    }
}
