package com.example.demo1.domain.ports;

import com.example.demo1.domain.model.Services;

import java.util.List;

public interface ServicesRepository {
    List<Services> getAllServices();
}
