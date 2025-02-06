package com.example.application.services;

import com.example.application.domain.model.Services;
import com.example.application.domain.ports.ServicesRepository;

import java.util.List;

public class ServicesService {
    // On définit un service qui contient la logique metier pour gérer les salariés
    // On instancie l'interface Repository se situant dans le port pour effectuer la recherche
    // Ce service sert de pont entre la logique métier et les adaptateurs
    // Obligé de creer le constructeur si la methode est en final, car une seule implementation possible
    // Dependance essentielle dans le service donc pour le proteger mettre en final
    // Il y a injection de dépendance
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
