package com.example.demo1.application;

import com.example.demo1.domain.model.Services;
import com.example.demo1.domain.ports.ServicesRepository;

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
