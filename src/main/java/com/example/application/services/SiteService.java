package com.example.application.services;

import com.example.application.domain.model.Site;
import com.example.application.domain.ports.SiteRepository;

import java.util.List;

public class SiteService {
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
