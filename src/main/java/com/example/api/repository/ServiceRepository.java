package com.example.api.repository;

import com.example.api.model.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Services, Integer> {
}
