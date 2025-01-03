package com.example.application.domain.ports;

import com.example.application.domain.model.Services;

import java.util.List;

public interface ServicesRepository {
    List<Services> getAllServices();
    Services createService(Services service);
    Services updateService(Services service);
    boolean deleteService(int id);
}
