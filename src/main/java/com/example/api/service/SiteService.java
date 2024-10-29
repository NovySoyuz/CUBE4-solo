package com.example.api.service;

import com.example.api.model.Site;
import com.example.api.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    @Autowired
    private SiteRepository siteRepository;

    public Site createSite(String city) {
        Site site = new Site();
        site.setCity(city);

        return siteRepository.save(site);
    }

    public Site updateSite(int id, String city) {
        Site siteUpdate = siteRepository.findById(id).orElseThrow(() -> new RuntimeException("Site not found"));
        siteUpdate.setCity(city);

        return siteRepository.save(siteUpdate);
    }

    public Iterable<Site> getAllSite() {
        return siteRepository.findAll();
    }

    public void deleteSite (int id)  {
        Site siteDelete = siteRepository.findById(id).orElseThrow(() -> new RuntimeException("Site not found"));
        siteRepository.delete(siteDelete);
    }

}
