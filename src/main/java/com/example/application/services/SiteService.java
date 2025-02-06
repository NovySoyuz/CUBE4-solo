package com.example.application.services;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;

import java.util.List;

public class SiteService {
    // On définit un service qui contient la logique metier pour gérer les salariés
    // On instancie l'interface Repository se situant dans le port pour effectuer la recherche
    // Ce service sert de pont entre la logique métier et les adaptateurs
    // Obligé de creer le constructeur si la methode est en final, car une seule implementation possible
    // Dependance essentielle dans le service donc pour le proteger mettre en final
    // Il y a injection de dépendance
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSite () {
        return siteRepository.getAllSite();
    }
    public Site createSite(Site site) {
        return siteRepository.createSite(site);
    }
    public Site updateSite(Site site) {
        return siteRepository.updateSite(site);
    }
    public boolean deleteSite(int id) {
        return siteRepository.deleteSite(id);
    }
}
