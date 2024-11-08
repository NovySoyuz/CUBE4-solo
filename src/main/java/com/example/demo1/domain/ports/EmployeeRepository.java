package com.example.demo1.domain.ports;

import com.example.demo1.domain.model.Employee;

import java.util.List;
// interface qui définit les intéractions possibles entre la couche modéle et les couches externes
// Ici cela permet à la couche application d'interagir avec le domaine sans se soucier de la source des données

public interface EmployeeRepository {
    List<Employee> findBySite (String site);
    List<Employee> findByName (String name);
    List<Employee> findByService (String service);
}
