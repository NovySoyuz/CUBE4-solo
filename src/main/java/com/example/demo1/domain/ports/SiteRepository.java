package com.example.demo1.domain.ports;

import com.example.demo1.domain.model.Site;

import java.util.List;

public interface SiteRepository {
    List<Site> getAllSite();
}
