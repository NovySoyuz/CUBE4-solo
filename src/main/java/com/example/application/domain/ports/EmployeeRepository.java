package com.example.application.domain.ports;

import com.example.application.domain.model.Employee;

import java.util.List;
// interface qui définit les intéractions possibles entre la couche modéle et les couches externes
// Ici cela permet à la couche application d'interagir avec le domaine sans se soucier de la source des données

public interface EmployeeRepository {
    List<Employee> findBySite (int site);
    List<Employee> findByName (String name);
    List<Employee> findByService (int service);
    // La fonction retourne un objet de type Employee, une fois l'objet créé, un nouvel objet représentant
    // cet employé est retrourné, avec les info mise à jours (id)
    // La methode prend en argument un objet employé avec les info adequats
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();

}
