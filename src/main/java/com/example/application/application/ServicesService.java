package com.example.application.application;

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
}
