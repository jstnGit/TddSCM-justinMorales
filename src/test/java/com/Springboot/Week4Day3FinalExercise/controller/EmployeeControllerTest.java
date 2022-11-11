package com.Springboot.Week4Day3FinalExercise.controller;

import com.Springboot.Week4Day3FinalExercise.model.Employee;
import com.Springboot.Week4Day3FinalExercise.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class EmployeeControllerTest {

    @MockBean
    private EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Employee test1 = new Employee(1L, "test1", 23, 30000d, "SE");
    Employee test2 = new Employee(2L, "test2", 30, 200000d, "SE");


    @Test
    @DisplayName("GIVEN employees from SQL in data.sql" +
            "WHEN getEmployee is executed" +
            "THEN result should return allEmployees")
    void testGetEmployee() throws Exception {
        //arrange
        when(employeeServiceImpl.findAllEmployee()).thenReturn(List.of(test1, test2));
        //act
        // assert
        mockMvc.perform(get("/employee")).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(test1.getId()))
                .andExpect(jsonPath("$.[0].name").value(test1.getName()))
                .andExpect(jsonPath("$.[0].age").value(test1.getAge()))
                .andExpect(jsonPath("$.[1].id").value(test2.getId()))
                .andExpect(jsonPath("$.[1].name").value(test2.getName()))
                .andExpect(jsonPath("$.[1].age").value(test2.getAge()));

    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql" +
            "WHEN getEmployeeById is executed with value = 1" +
            "THEN result should return test1")
    void testGetEmployeeById() throws Exception {
        //arrange
        when(employeeServiceImpl.findEmployeeById(anyLong())).thenReturn(test1);
        //act
        // assert
        mockMvc.perform(get("/employee/{id}",1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(test1.getId()))
                .andExpect(jsonPath("$.name").value(test1.getName()))
                .andExpect(jsonPath("$.age").value(test1.getAge()));

    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql" +
            "WHEN getEmployeeById is executed with value = 1" +
            "THEN result should return test1")
    void testSaveEmployee() throws Exception {
        //arrange
        when(employeeServiceImpl.saveEmployee(any(Employee.class))).thenReturn(test1);
        //act
        // assert
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(test1.getId()))
                .andExpect(jsonPath("$.name").value(test1.getName()))
                .andExpect(jsonPath("$.age").value(test1.getAge()));

    }
}
