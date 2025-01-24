package com.example.api.service;

import com.example.api.dto.AdminConnectionDTO;
import com.example.api.model.Employees;
import com.example.api.repository.EmployeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Composant de service metier

public class EmployeeService {
    @Autowired // Injecte automatiquement les dépendances dans les attributs (injection de dépendance)
    private EmployeesRepository employeesRepository; // Instance de EmployeeRepository permet l'accés aux données de Employees

      // Création d'un utilisateur
    // Type de retour Employees cela signifie qu'une fois la methode éxécutée elle va retourner un objet de type Employees
    public Employees createEmployee(Employees employees) {
        return employeesRepository.save(employees);
    }

    public Employees updateEmployee(Employees employees) {

        return employeesRepository.save(employees);
    }

    // Récuperation des utilisateurs
    public Iterable<Employees> getAllEmployees() {
        // retourne un iterable utilise pour afficher une liste compléte
        return employeesRepository.findAll();
    }

    // Récuperation d'un employé par id
    public Optional<Employees> getEmployeeById(Integer id) {
        // Optionnal (peut contenir ou id ou etre vide si aucun employé n'est trouvé
        return employeesRepository.findById(id);
    }

    // Récuperation des employés par un nom
    public List<Employees> getEmployeesByName(String name) {
        return employeesRepository.findByNameIgnoreCase(name);
    }

    public List<Employees> getEmployeesByServices(Integer id) {
        return employeesRepository.findByServices_id(id);
    }

    public List<Employees> getEmployeesBySite(Integer id) {
        return employeesRepository.findBySite_id(id);
    }

    // Supprimer un employé par son ID
    public void deleteEmployee(Integer id) {
        employeesRepository.deleteById(id);
    }
    // Transmission des données avec la couche controller
    // On récupere les données voulues de employees en les mettant dans le DTO
    public AdminConnectionDTO adminLogin(String name, String firstname) {
        Employees employees = employeesRepository.loginAdmin(name, firstname);
        AdminConnectionDTO adminConnectionDTO = new AdminConnectionDTO();
        adminConnectionDTO.setName(employees.getName());
        adminConnectionDTO.setFirstname(employees.getFirstname());
        adminConnectionDTO.setIs_admin(employees.getIs_admin());

        return adminConnectionDTO;
    }

    // 1) Utilisation de Iterable :
    //
    //Utilisez Iterable lorsque vous ne prévoyez que de lire les données séquentiellement
    // et que vous n'avez pas besoin de fonctionnalités avancées de List.
    //
    // 2) Préférence pour List :
    //
    //Utilisez List lorsque vous avez besoin de fonctionnalités spécifiques de listes,
    // ou lorsque l'ordre des éléments est important et que des manipulations
    // supplémentaires sont nécessaires.

}