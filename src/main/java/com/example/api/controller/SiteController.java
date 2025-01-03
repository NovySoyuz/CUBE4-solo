package com.example.api.controller;

import com.example.api.model.Site;
import com.example.api.service.SiteService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site/")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @PostMapping()
    public ResponseEntity<Site> createSite (@RequestBody Site site) {
        Site createdSite = siteService.createSite(
                site.getCity()
        );
        return ResponseEntity.ok(createdSite);
    }

    @PutMapping("{id}")
    public ResponseEntity<Site> updateSite (@PathVariable int id, @RequestBody Site site) {
        Site siteUpdate = siteService.updateSite(
                id,
                site.getCity()
        );
        return ResponseEntity.ok(siteUpdate);
    }

    @GetMapping("search")
    public ResponseEntity<Iterable<Site>> getAllSite () {
        Iterable site = siteService.getAllSite();

        return ResponseEntity.ok(site);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSite (@PathVariable int id) {
        String message = "Suppression success";
        siteService.deleteSite(id);

        return ResponseEntity.ok(message);
    }

}
