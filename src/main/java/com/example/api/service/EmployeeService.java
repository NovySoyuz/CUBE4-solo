package com.example.api.service;

import com.example.api.model.Employees;
import com.example.api.model.Services;
import com.example.api.model.Site;
import com.example.api.repository.EmployeesRepository;

import com.example.api.repository.ServiceRepository;
import com.example.api.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Composant de service metier

public class EmployeeService {
    @Autowired // Injecte automatiquement les dépendances dans les attributs (injection de dépendance)
    private EmployeesRepository employeesRepository; // Instance de EmployeeRepository permet l'accés aux données de Employees

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SiteRepository siteRepository;

    // Création d'un utilisateur
    // Type de retour Employees cela signifie qu'une fois la methode éxécutée elle va retourner un objet de type Employees
    public Employees createEmployee(String name, String firstname, String phone, String mail, Integer serviceId, Integer siteId) {
        // Il faut d'abord instancier la classe dans le modéle
        // Récupération du service associé à l'ID. Si identifiant invalide lance une RuntimeException
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new RuntimeException("Erruer"));
        Services service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));

        // Instancie la classe employees
        Employees employees = new Employees();
        employees.setName(name);
        employees.setFirstname(firstname);
        employees.setPhone(phone);
        employees.setMail(mail);
        employees.setSite(site);
        employees.setServices(service);

        return employeesRepository.save(employees);
    }

    public Employees updateEmployee(int id, String name, String firstname, String phone, String mail, Integer serviceId, Integer siteId) {
        Employees employees = employeesRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new RuntimeException("Site not found"));
        Services services = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));

        employees.setName(name);
        employees.setFirstname(firstname);
        employees.setPhone(phone);
        employees.setMail(mail);
        employees.setServices(services);
        employees.setSite(site);

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