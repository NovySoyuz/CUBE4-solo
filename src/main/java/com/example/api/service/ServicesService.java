package com.example.api.service;

import com.example.api.model.Services;
import com.example.api.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    @Autowired
    private ServiceRepository serviceRepository;

    public Services createServices(String name) {
        Services services = new Services();
        services.setName(name);
        return serviceRepository.save(services);
    }

    public Services updateServices(int id, String name) {
        Services servicesFound = serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Id non reconnu"));

        servicesFound.setName(name);

        return serviceRepository.save(servicesFound);
    }

    public Iterable<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public void deleteService(int id) {
        // Récuperation de l'id employé
        // serviceRepository.findById(id. On interroge directement la bdd
        Services services = serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service non trouvé"));
        serviceRepository.delete(services);
    }
}
