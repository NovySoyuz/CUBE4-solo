package com.example.application.services;

import com.example.application.domain.model.Services;
import com.example.application.domain.ports.ServicesRepository;

import java.util.List;

public class ServicesService {
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices () {
        return servicesRepository.getAllServices();
    }
    public Services createService(Services service) {
        return servicesRepository.createService(service);
    }
    public Services updateService(Services service) {
        return servicesRepository.updateService(service);
    }
    public boolean deleteService(int id) {
        return servicesRepository.deleteService(id);
    }
}
