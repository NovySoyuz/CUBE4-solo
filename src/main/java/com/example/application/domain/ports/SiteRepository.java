package com.example.application.domain.ports;

import com.example.application.domain.model.Site;

import java.util.List;

public interface SiteRepository {
    List<Site> getAllSite();
    Site createSite(Site site);
    Site updateSite(Site site);
    boolean deleteSite(int id);
}
