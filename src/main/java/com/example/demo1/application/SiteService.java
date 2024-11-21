package com.example.demo1.application;

import com.example.demo1.domain.model.Site;
import com.example.demo1.domain.ports.SiteRepository;

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
