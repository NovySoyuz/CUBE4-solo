package com.example.api.controller;

import com.example.api.dto.AdminConnectionDTO;
import com.example.api.model.Employees;
import com.example.api.model.Services;
import com.example.api.model.Site;
import com.example.api.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.lang.reflect.Array;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;
    Services mockServices = new Services();
    Employees mockEmployees = new Employees();
    AdminConnectionDTO mockAdmin = new AdminConnectionDTO();
    Site mockSite = new Site();
    @Test
    void getEmployeeByName() throws Exception{
        mockEmployees.setName("testName");
        mockEmployees.setId_employee(1);
        mockEmployees.setFirstname("testFirstname");

        Mockito.when(employeeService.getEmployeesByName("testName")).thenReturn(Collections.singletonList(mockEmployees));

        mockMvc.perform(get("/api/employees/search")
                .param("name","testName")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_employee").value(1))
                .andExpect(jsonPath("$[0].name").value("testName"))
                .andExpect(jsonPath("$[0].firstname").value("testFirstname"));
    }

    @Test
    void getEmployeeByService() throws Exception {
        mockServices.setId(1);
        mockServices.setName("testServiceName");

        mockEmployees.setId_employee(1);
        mockEmployees.setFirstname("testFirstname");
        mockEmployees.setServices(mockServices);

        Mockito.when(employeeService.getEmployeesByServices(1)).thenReturn(Collections.singletonList(mockEmployees));

        mockMvc.perform(get("/api/employees/service/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("testFirstname"))
                .andExpect(jsonPath("$[0].id_employee").value(1))
                .andExpect(jsonPath("$[0].services.name").value("testServiceName"));
    }

    @Test
    void getEmployeesBySite() throws Exception {
        mockSite.setCity("testCity");
        mockSite.setId(1);

        mockEmployees.setName("testName");
        mockEmployees.setSite(mockSite);

        Mockito.when(employeeService.getEmployeesBySite(1)).thenReturn(Collections.singletonList(mockEmployees));

        mockMvc.perform(get("/api/employees/site/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("testName"))
                .andExpect(jsonPath("$[0].site.city").value("testCity"));
    }

    @Test
    void adminLogin() throws Exception {
        mockAdmin.setIs_admin(1);
        mockAdmin.setName("testName");
        mockAdmin.setFirstname("testFirstname");

        Mockito.when(employeeService.adminLogin("testName", "testFirstname")).thenReturn(mockAdmin);

        mockMvc.perform(post("/api/employees/admin")
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.firstname").value("testFirstname"))
                .andExpect(jsonPath("$.is_admin").value(1));
    }
}