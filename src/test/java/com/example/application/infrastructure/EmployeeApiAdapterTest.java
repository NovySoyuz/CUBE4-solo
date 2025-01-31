package com.example.application.infrastructure;

import com.example.application.domain.model.Employee;
import com.example.application.utils.HttpApiAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeApiAdapterTest {

    private HttpApiAdapter httpApiAdapterMock;
    private EmployeeApiAdapter employeeApiAdapter;

    @BeforeEach
    void setUp() {
        httpApiAdapterMock = Mockito.mock(HttpApiAdapter.class);
        employeeApiAdapter = new EmployeeApiAdapter(httpApiAdapterMock);
    }

    @Test
    void testFindBySite() {
        int siteId = 1;
        Employee employee1 = new Employee();
        employee1.setId_employee(1);
        employee1.setName("toto");

        Employee employee2 = new Employee();
        employee2.setId_employee(2);
        employee2.setName("tutu");

        List<Employee> expectedEmployees = Arrays.asList(employee1,employee2);

        Mockito.when(httpApiAdapterMock.sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class)))
                .thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApiAdapter.findBySite(siteId);

        assertEquals(expectedEmployees, actualEmployees);
        Mockito.verify(httpApiAdapterMock).sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class));
    }

    @Test
    void testFindByName() {
        String name = "John";
        Employee employee1 = new Employee();
        employee1.setId_employee(1);
        employee1.setName(name);

        List<Employee> expectedEmployees = Arrays.asList(employee1);

        Mockito.when(httpApiAdapterMock.sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class)))
                .thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApiAdapter.findByName(name);

        assertEquals(expectedEmployees, actualEmployees);
        Mockito.verify(httpApiAdapterMock).sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class));
    }

    @Test
    void testFindByService() {
        int serviceId = 2;
        Employee employee1 = new Employee();
        employee1.setId_employee(1);
        employee1.setName("toto");

        Employee employee2 = new Employee();
        employee2.setId_employee(2);
        employee2.setName("tutu");

        List<Employee> expectedEmployees = Arrays.asList(employee1,employee2);

        Mockito.when(httpApiAdapterMock.sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class)))
                .thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApiAdapter.findByService(serviceId);

        assertEquals(expectedEmployees, actualEmployees);
        Mockito.verify(httpApiAdapterMock).sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class));
    }

    @Test
    void testCreateEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setId_employee(1);
        newEmployee.setName("toto");

        Mockito.when(httpApiAdapterMock.sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                        Mockito.eq(newEmployee), Mockito.eq(Employee.class)))
                .thenReturn(newEmployee);

        Employee createdEmployee = employeeApiAdapter.createEmployee(newEmployee);

        assertEquals(newEmployee, createdEmployee);
        Mockito.verify(httpApiAdapterMock).sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                Mockito.eq(newEmployee), Mockito.eq(Employee.class));
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId_employee(1);
        employee1.setName("toto");

        Employee employee2 = new Employee();
        employee2.setId_employee(2);
        employee2.setName("tutu");

        List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

        Mockito.when(httpApiAdapterMock.sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class)))
                .thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeApiAdapter.getAllEmployees();

        assertEquals(expectedEmployees, actualEmployees);
        Mockito.verify(httpApiAdapterMock).sendGetAllRequest(Mockito.anyString(), Mockito.eq(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        int employeeId = 2;

        Mockito.when(httpApiAdapterMock.deleteMethodById(Mockito.anyString())).thenReturn(true);

        boolean result = employeeApiAdapter.deleteEmployee(employeeId);

        assertTrue(result);
        Mockito.verify(httpApiAdapterMock).deleteMethodById(Mockito.anyString());
    }

    @Test
    void testUpdateEmployee() {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId_employee(1);
        updatedEmployee.setName("toto");

        Mockito.when(httpApiAdapterMock.sendDataRequest(Mockito.anyString(), Mockito.eq("PUT"),
                        Mockito.eq(updatedEmployee), Mockito.eq(Employee.class)))
                .thenReturn(updatedEmployee);

        Employee result = employeeApiAdapter.updateEmployee(updatedEmployee);

        assertEquals(updatedEmployee, result);
        Mockito.verify(httpApiAdapterMock).sendDataRequest(Mockito.anyString(), Mockito.eq("PUT"),
                Mockito.eq(updatedEmployee), Mockito.eq(Employee.class));
    }

    @Test
    void testLoginAdmin() {
        Employee admin = new Employee();
        admin.setId_employee(1);
        admin.setName("toto");

        Mockito.when(httpApiAdapterMock.sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                        Mockito.eq(admin), Mockito.eq(Employee.class)))
                .thenReturn(admin);

        Employee result = employeeApiAdapter.loginAdmin(admin);

        assertEquals(admin, result);
        Mockito.verify(httpApiAdapterMock).sendDataRequest(Mockito.anyString(), Mockito.eq("POST"),
                Mockito.eq(admin), Mockito.eq(Employee.class));
    }
}