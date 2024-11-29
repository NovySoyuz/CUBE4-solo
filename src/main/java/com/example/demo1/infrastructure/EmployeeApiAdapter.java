package com.example.demo1.infrastructure;

import com.example.demo1.domain.model.Employee;
import com.example.demo1.domain.model.Services;
import com.example.demo1.domain.model.Site;
import com.example.demo1.domain.ports.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeApiAdapter implements EmployeeRepository {
    // Chemin de l'API
    private final String apiURL = "http://localhost:8080/api/employees";

    // Implementation de la methode présente dans EmployeeRepository
    @Override
    public List<Employee> findBySite(int site) {
        List<Employee> employees;
        try {
            URL url = new URL(apiURL + "/site/" + site);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(connection.getInputStream(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
        } catch (Exception e) {
            throw new RuntimeException("Erreur dans la recherche du site", e);
        }
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        // Stocker les objets employés dans la liste
        List<Employee> employees;
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
            throw new RuntimeException("Pas de salariés", e);
        }
        return employees;
    }

    @Override
    public List<Employee> findByService(int service) {
        List<Employee> employees;
        try {
            URL url = new URL(apiURL + "/service/" + service);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(connection.getInputStream(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
        } catch (Exception e) {
            throw new RuntimeException("Pas de service", e);
        }
        return employees;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // Les données envoyées sont au format JSON
            connection.setRequestProperty("Content-Type", "application/json");
            // Permet d'envoyer des requetes dans le corps de la requete
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            // Convertit l'objet Employee en JSON
            String jsonEmployee = mapper.writeValueAsString(employee);
            // Ouvre un flux de sortie pour écrire
            try (OutputStream os = connection.getOutputStream()) {
                // Convertit le JSON en tableau d'octets et l'écrit dans le flux
                os.write(jsonEmployee.getBytes());
                // Vide le flux pour s'assurer que les données sont envoyées
                os.flush();
            }
            if (connection.getResponseCode() == 200) {
                // Désérialisation de la réponse JSON en un objet Employee
                return mapper.readValue(connection.getInputStream(), Employee.class);
            } else {
                throw new RuntimeException("Erreur lors de la création de l'employé : " + connection.getResponseMessage());

            }
        } catch (Exception e) {
            throw new RuntimeException("Echec de la création de l'employé", e);
        }
    }

}
