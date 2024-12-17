package com.example.application.domain.ports;

import com.example.application.domain.model.Services;

import java.util.List;

public interface ServicesRepository {
    List<Services> getAllServices();
}
