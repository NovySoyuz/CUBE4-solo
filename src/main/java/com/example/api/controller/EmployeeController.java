package com.example.api.controller;

import com.example.api.model.Employees;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Indique que cette classe est un bean
// Les methodes renvoient directement du JSON/XML(reponse HTTP)
// RESTful chaque methode HTPP(GET,POST,DELETE,UPDATE) correspond à une action spécifique
@RestController
// Chemin de base de l'API
@RequestMapping("/api/employees/")
public class EmployeeController {
    @Autowired
    // Injecte automatiquement l'instance de EnployeeService dans le controlleur
    // Permet d'appeler les methodes du service pour éffectuer les opérations
    private EmployeeService employeeService;

    // endpoint création de l'employé
    @PostMapping // Methode POST
    // @RequestBody indique que le corp de la requete contient un objet JSON/XML
    // Qui doit etre converti en instance de Employees
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees) {
        Employees createdEmployee = employeeService.createEmployee(

                employees.getName(),
                employees.getFirstname(),
                employees.getPhone(),
                employees.getMail(),
                // Il faut aller chercher dans la classe Service en passant par Employees
                // Car clé étrangére
                employees.getServices().getId(),
                employees.getSite().getId()

        );
        // renvoie un objet ResponsEntity avec http 200
        // Représente l'ensemble de la réponse http (status code, header, body)
        return ResponseEntity.ok(createdEmployee);
    }
    // @PathVariable extrait la variable de chemin ici id
    @PutMapping("{id}")
    public ResponseEntity<Employees> updateEmployee (@PathVariable Integer id, @RequestBody Employees employees) {
        Employees update = employeeService.updateEmployee(
                id,
                employees.getName(),
                employees.getFirstname(),
                employees.getPhone(),
                employees.getMail(),
                employees.getServices().getId(),
                employees.getSite().getId()
        );
        return ResponseEntity.ok(update);
    }

    // Récupératio de l'ensemble des employes
    @GetMapping // Methode GET
    public ResponseEntity<Iterable<Employees>> getAllEmployees() {
        Iterable<Employees> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Récuperation d'un employé par son id
    // {id} est une variable de chemin capturé par @PathVariable
    // Réponse au requete GET /api/employees/{id}
    @GetMapping("{id}")
    // @PathVariable extrait la variable id de l'url pour l'utiliser dans la methode
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Integer id) {
        Optional<Employees> employees = employeeService.getEmployeeById(id);
        //utilisation de map car Optionnal dans le service
        // Cela évite les null et les erreurs de pointeur null
        // ResponseEntity.notFound().build() = renvoie erreur 404
        return employees.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récuperation d'un employé par le nom
    @GetMapping("search")
    // @RequestParam = extrait le parametre name de la requette HTTP aprés ? dans l'url exemple : /search?name = toto
    public ResponseEntity<List<Employees>> getEmployeeByName(@RequestParam String name) {
        List<Employees> employees = employeeService.getEmployeesByName(name);

        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employees);
    }
    // Obtenir les employés par le service
    @GetMapping("service/{id}")
    public ResponseEntity<List<Employees>> getEmployeeByService(@PathVariable Integer id) {
        List<Employees> employees = employeeService.getEmployeesByServices(id);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping("site/{id}")
    public ResponseEntity<List<Employees>> getEmployeesBySite(@PathVariable Integer id) {
        List<Employees> employees = employeeService.getEmployeesBySite(id);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }
    // Suppression d'un employé
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        String message = "Success suppress";

        employeeService.deleteEmployee(id);
        // Reponse 204 pour indiquer la suppression avec succés
        return ResponseEntity.ok(message);
    }
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