package com.example.demo1.application;

import com.example.demo1.domain.model.Employee;
import com.example.demo1.domain.ports.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    // On définit un service qui contient la logique metier pour gérer les salariés
    // On instancie l'interface Repository se situant dans le port pour effectuer la recherche
    // Ce service sert de pont entre la logique métier et les adaptateurs
    // Obligé de creer le constructeur si la methode est en final, car une seule implementation possible
    // Dependance essentielle dans le service donc pour le proteger mettre en final
    // Il y a injection de dépendance
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> searchBySite(String site) {
        return employeeRepository.findBySite(site);
    }

    public List<Employee> searchByService(String service) {
        return employeeRepository.findByService(service);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByName(name);
    }
}
