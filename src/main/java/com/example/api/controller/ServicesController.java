package com.example.api.controller;


import com.example.api.model.Services;
import com.example.api.repository.ServiceRepository;
import com.example.api.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services/")
public class ServicesController {
    @Autowired
    private ServicesService service;

    @PostMapping
    public ResponseEntity<Services> createService (@RequestBody Services services) {
        Services createdService = service.createServices(
                services.getName()
        );
        return ResponseEntity.ok(createdService);
    }
    @PutMapping("{id}")
    public ResponseEntity<Services> updateService (@PathVariable int id, @RequestBody Services services) {
        Services update = service.updateServices(
                id,
                services.getName()
        );
        return ResponseEntity.ok(update);
    }

    @GetMapping("search")
    public ResponseEntity<Iterable<Services>> getAllServices () {
        Iterable<Services> servicesList = service.getAllServices();
        return ResponseEntity.ok(servicesList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteService (@PathVariable int id) {
        String name = "Suppress success";
        service.deleteService(id);

        return ResponseEntity.ok(name);
    }

}
