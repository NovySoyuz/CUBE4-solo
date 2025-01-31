package com.example.application.services;

import com.example.application.domain.model.Employee;
import com.example.application.domain.model.Services;
import com.example.application.domain.model.Site;
import com.example.application.domain.ports.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepositoryMock;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        // Mock du port
        employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        // Service utilisant le mock
        employeeService = new EmployeeService(employeeRepositoryMock);
    }

    @Test
    void testSearchBySite() {
        int siteId = 1;

        Site site = new Site();
        site.setCity("test");
        site.setId(siteId);

        Employee employee1 = new Employee();
        employee1.setName("toto");
        employee1.setSite(site);

        Employee employee2 = new Employee();
        employee2.setName("tutu");
        employee2.setSite(site);

        List<Employee> expectedEmployee = Arrays.asList(employee1,employee2);
        //Quand on appel findBySite il doit retourner nos 2 employés
        Mockito.when(employeeRepositoryMock.findBySite(siteId)).thenReturn(expectedEmployee);
        // Appel de la vraie methode, elle va faire appel à employeeRepositoryMock.findBySite(siteId)
        List<Employee> actualEmployee = employeeService.searchBySite(siteId);
        // Comparaison du résultat réel et du résultat attendu
        assertEquals(expectedEmployee,actualEmployee);
        // Verifie que la methode mocké est appelé
        Mockito.verify(employeeRepositoryMock).findBySite(siteId);
    }

    @Test
    void testCreateEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setName("toto");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setName("toto");
        expectedEmployee.setId_employee(1);

        Mockito.when(employeeRepositoryMock.createEmployee(newEmployee)).thenReturn(expectedEmployee);

        Employee createEmployee = employeeService.createEmployee(newEmployee);

        assertEquals(expectedEmployee, createEmployee);
        Mockito.verify(employeeRepositoryMock).createEmployee(newEmployee);

    }

    @Test
    void searchByService() {
        int serviceId = 1;

        Services services = new Services();
        services.setName("toto");
        services.setId(serviceId);

        Employee employee1 = new Employee();
        employee1.setName("toto");
        employee1.setServices(services);

        Employee employee2 = new Employee();
        employee2.setName("tutu");
        employee2.setServices(services);

        List<Employee> expectedEmployee = Arrays.asList(employee1,employee2);

        Mockito.when(employeeRepositoryMock.findByService(serviceId)).thenReturn(expectedEmployee);

        List<Employee> actualEmployee = employeeService.searchByService(serviceId);

        assertEquals(expectedEmployee, actualEmployee);
        Mockito.verify(employeeRepositoryMock).findByService(serviceId);


    }

    @Test
    void searchByName() {
        String searchName = "toto";

        Employee employee1 = new Employee();
        employee1.setName(searchName);

        Employee employee2 = new Employee();
        employee2.setName(searchName);

        List<Employee> expectedEmployee = Arrays.asList(employee1,employee2);
        Mockito.when(employeeRepositoryMock.findByName(searchName)).thenReturn(expectedEmployee);

        List<Employee> actualEmployee = employeeService.searchByName(searchName);

        assertEquals(actualEmployee, expectedEmployee);
        Mockito.verify(employeeRepositoryMock).findByName(searchName);
    }

    @Test
    void getAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setName("toto");

        Employee employee2 = new Employee();
        employee2.setName("tutu");

        List<Employee> expectedEmployee = Arrays.asList(employee1, employee2);
        Mockito.when(employeeRepositoryMock.getAllEmployees()).thenReturn(expectedEmployee);

        List<Employee> actualEmployee = employeeService.getAllEmployees();

        assertEquals(actualEmployee, expectedEmployee);
        Mockito.verify(employeeRepositoryMock).getAllEmployees();

    }

    @Test
    void deleteEmployee() {
        Employee employee1 = new Employee();
        employee1.setName("toto");
        int employeeId = 1;

        // Simulation du comportement du repository : Suppression réussie
        Mockito.when(employeeRepositoryMock.deleteEmployee(employeeId)).thenReturn(true);

        // Appel de la méthode réelle du service
        boolean isDeleted = employeeService.deleteEmployee(employeeId);

        // Vérification du résultat
        assertTrue(isDeleted);

        // Vérification que la méthode mockée a bien été appelée
        Mockito.verify(employeeRepositoryMock).deleteEmployee(employeeId);
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setId_employee(1);
        employee.setName("Updated Name");

        Mockito.when(employeeRepositoryMock.updateEmployee(employee)).thenReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(employee);

        assertEquals(employee, updatedEmployee);

        Mockito.verify(employeeRepositoryMock).updateEmployee(employee);
    }

    @Test
    void adminConnection() {
        Employee admin = new Employee();
        admin.setId_employee(1);
        admin.setName("Admin");

        Mockito.when(employeeRepositoryMock.loginAdmin(admin)).thenReturn(admin);

        Employee loggedInAdmin = employeeService.adminConnection(admin);

        assertEquals(admin, loggedInAdmin);

        Mockito.verify(employeeRepositoryMock).loginAdmin(admin);
    }
}