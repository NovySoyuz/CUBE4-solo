package com.example.api.service;

import com.example.api.dto.AdminConnectionDTO;
import com.example.api.model.Employees;
import com.example.api.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
    @SpringBootTest
    class EmployeeServiceTest {

        @MockBean
        private EmployeesRepository employeesRepository;

        @Autowired
        private EmployeeService employeeService;

        Employees mockEmployee = new Employees();

        @Test
        void createEmployee() {
            mockEmployee.setId_employee(1);
            mockEmployee.setName("Dupont");

            Mockito.when(employeesRepository.save(Mockito.any(Employees.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Employees createdEmployee = employeeService.createEmployee(mockEmployee);

            assertNotNull(createdEmployee);
            assertEquals(1, createdEmployee.getId_employee());
            assertEquals("Dupont", createdEmployee.getName());
            Mockito.verify(employeesRepository, Mockito.times(1)).save(Mockito.any(Employees.class));
        }

        @Test
        void updateEmployee() {
            mockEmployee.setId_employee(1);
            mockEmployee.setName("Martin");

            Mockito.when(employeesRepository.save(Mockito.any(Employees.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Employees updatedEmployee = employeeService.updateEmployee(mockEmployee);

            assertNotNull(updatedEmployee);
            assertEquals(1, updatedEmployee.getId_employee());
            assertEquals("Martin", updatedEmployee.getName());
            Mockito.verify(employeesRepository, Mockito.times(1)).save(mockEmployee);
        }

        @Test
        void getAllEmployees() {
            mockEmployee.setId_employee(1);
            mockEmployee.setName("Durand");

            Mockito.when(employeesRepository.findAll()).thenReturn(Collections.singletonList(mockEmployee));

            Iterable<Employees> allEmployees = employeeService.getAllEmployees();
            List<Employees> employeeList = StreamSupport.stream(allEmployees.spliterator(), false)
                    .collect(Collectors.toList());

            assertNotNull(employeeList);
            assertFalse(employeeList.isEmpty());
            assertEquals(1, employeeList.get(0).getId_employee());
            assertEquals("Durand", employeeList.get(0).getName());
        }

        @Test
        void getEmployeeById() {
            mockEmployee.setId_employee(1);
            mockEmployee.setName("Dubois");

            Mockito.when(employeesRepository.findById(1)).thenReturn(Optional.of(mockEmployee));

            Optional<Employees> foundEmployee = employeeService.getEmployeeById(1);

            assertTrue(foundEmployee.isPresent());
            assertEquals(1, foundEmployee.get().getId_employee());
            assertEquals("Dubois", foundEmployee.get().getName());
        }
        @Test
        void getEmployeesByName() {
            mockEmployee.setId_employee(1);
            mockEmployee.setName("Dupont");

            Mockito.when(employeesRepository.findByNameIgnoreCase("Dupont")).thenReturn(Collections.singletonList(mockEmployee));

            List<Employees> employeesByName = employeeService.getEmployeesByName("Dupont");

            assertNotNull(employeesByName);
            assertFalse(employeesByName.isEmpty());
            assertEquals(1, employeesByName.get(0).getId_employee());
            assertEquals("Dupont", employeesByName.get(0).getName());
        }

        @Test
        void getEmployeesByServices() {
            mockEmployee.setId_employee(1);

            Mockito.when(employeesRepository.findByServices_id(1)).thenReturn(Collections.singletonList(mockEmployee));

            List<Employees> employeesByService = employeeService.getEmployeesByServices(1);

            assertNotNull(employeesByService);
            assertFalse(employeesByService.isEmpty());
            assertEquals(1, employeesByService.get(0).getId_employee());
        }

        @Test
        void getEmployeesBySite() {
            mockEmployee.setId_employee(1);

            Mockito.when(employeesRepository.findBySite_id(1)).thenReturn(Collections.singletonList(mockEmployee));

            List<Employees> employeesBySite = employeeService.getEmployeesBySite(1);

            assertNotNull(employeesBySite);
            assertFalse(employeesBySite.isEmpty());
            assertEquals(1, employeesBySite.get(0).getId_employee());
        }

        @Test
        void deleteEmployee() {
            employeeService.deleteEmployee(1);

            Mockito.verify(employeesRepository, Mockito.times(1)).deleteById(1);
        }

        @Test
        void adminLogin() {
            mockEmployee.setName("Durand");
            mockEmployee.setFirstname("Paul");
            mockEmployee.setIs_admin(1);

            Mockito.when(employeesRepository.loginAdmin("Durand", "Paul")).thenReturn(mockEmployee);

            AdminConnectionDTO adminDTO = employeeService.adminLogin("Durand", "Paul");

            assertNotNull(adminDTO);
            assertEquals("Durand", adminDTO.getName());
            assertEquals("Paul", adminDTO.getFirstname());
            assertEquals(1, adminDTO.getIs_admin());
        }
    }