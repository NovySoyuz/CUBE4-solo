package com.example.application.application;

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
}
