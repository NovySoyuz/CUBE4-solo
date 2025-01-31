package com.example.application.infrastructure;

import com.example.application.domain.model.Services;
import com.example.application.domain.ports.ServicesRepository;
import com.example.application.utils.HttpApiAdapter;

import java.util.List;

public class ServicesApiAdapter implements ServicesRepository {
    private final HttpApiAdapter httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/services/";

    // Ajout d'un constructeur pour utiliser dans les classes de tests
    // Permet l'injection de httpApiAdapter
    public ServicesApiAdapter(HttpApiAdapter httpApiAdapter) {
        this.httpApiAdapter = httpApiAdapter;
    }
    // Lors de la création d'un ServiceApiAdapter il utilise toujours une instance de httpApiAdapter
    public ServicesApiAdapter() {
        this.httpApiAdapter = new HttpApiAdapter();
    }

    @Override
    public List<Services> getAllServices() {
        return httpApiAdapter.sendGetAllRequest(apiURL+"search", Services.class);
    }

    @Override
    public Services createService(Services service) {
        return httpApiAdapter.sendDataRequest(apiURL, "POST", service, Services.class);
    }

    @Override
    public Services updateService(Services service) {
        return httpApiAdapter.sendDataRequest(apiURL +service.getId(), "PUT", service, Services.class);
    }

    @Override
    public boolean deleteService(int id) {
        return httpApiAdapter.deleteMethodById(apiURL + id);
    }
}
