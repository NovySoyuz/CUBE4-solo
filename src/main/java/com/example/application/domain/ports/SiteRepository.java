package com.example.application.domain.ports;

import com.example.application.domain.model.Site;

import java.util.List;

public interface SiteRepository {
    List<Site> getAllSite();
}
