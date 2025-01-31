package com.example.application.infrastructure;

import com.example.application.domain.model.Employee;
import com.example.application.domain.ports.EmployeeRepository;
import com.example.application.utils.HttpApiAdapter;

import java.util.List;

public class EmployeeApiAdapter implements EmployeeRepository {
    // Chemin de l'API
    private final HttpApiAdapter httpApiAdapter;
    private final String apiURL = "http://localhost:8080/api/employees/";
    // Ajout d'un constructeur pour utiliser dans les classes de tests
    // Permet l'injection de httpApiAdapter
    public EmployeeApiAdapter(HttpApiAdapter httpApiAdapter) {
        this.httpApiAdapter = httpApiAdapter;
    }

    // Lors de la création d'un ServiceApiAdapter il utilise toujours une instance de httpApiAdapter
    public EmployeeApiAdapter () {
        this.httpApiAdapter = new HttpApiAdapter();
    }

    // Implementation de la methode présente dans EmployeeRepository
    @Override
    public List<Employee> findBySite(int site) {
        return httpApiAdapter.sendGetAllRequest(apiURL + "site/" + site, Employee.class);
    }

    @Override
    public List<Employee> findByName(String name) {
        return httpApiAdapter.sendGetAllRequest(apiURL + "search?name=" + name, Employee.class);
    }

    @Override
    public List<Employee> findByService(int service) {
        return httpApiAdapter.sendGetAllRequest(apiURL + "service/" + service, Employee.class);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return httpApiAdapter.sendDataRequest(apiURL, "POST", employee, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return httpApiAdapter.sendGetAllRequest(apiURL, Employee.class);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return httpApiAdapter.deleteMethodById(apiURL+id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return httpApiAdapter.sendDataRequest(apiURL+ employee.getId_employee(), "PUT", employee, Employee.class);
    }

    @Override
    public Employee loginAdmin(Employee employee) {
        return httpApiAdapter.sendDataRequest(apiURL + "admin", "POST", employee, Employee.class);
    }


}
