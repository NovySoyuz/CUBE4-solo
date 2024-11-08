package com.example.demo1.infrastructure;

import com.example.demo1.domain.model.Employee;
import com.example.demo1.domain.ports.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeApiAdapter implements EmployeeRepository {
    // Chemin de l'API
    private final String apiURL = "http://localhost:8080/api/employees";

    // Implementation de la methode présente dans EmployeeRepository
    @Override
    public List<Employee> findBySite(String site) {
        return null;
    }

    @Override
    public List<Employee> findByName(String name) {
        // Stocker les objets employés dans la liste
        List<Employee> employees = new ArrayList<>();
        try {
            // Exemple d'une requete avec la parametre name
            // Mettre le meme chemin que dans l'API
            URL url = new URL(apiURL + "/search?name=" + name);
            // Ouverture de la connexion
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Methode GET
            connection.setRequestMethod("GET");
            // Utilisation de la librairie jackson
            // Lecture de la reponse JSON de l'API
            ObjectMapper mapper = new ObjectMapper();
            // Lecture du flux de réponse (inputstream) et désérialise le JSON en une liste d'objet employee
            // Désérialoisation : Convertir une structure textuelle (JSON en liste d'objet)
            // Exemplle : { "id": 1, "name": "Alice", "site": "Paris" } Employee employee1 = new Employee(1, "Alice", "Paris");
            employees = mapper.readValue(connection.getInputStream(),
                    // constructCollectionType précise à ObjectMapper qu'il doit traiter la réponse comme une liste d'objet
                    mapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Le nom n'est pas dans la liste");
        }
        return employees;
    }

    @Override
    public List<Employee> findByService(String service) {
        return null;
    }
}
