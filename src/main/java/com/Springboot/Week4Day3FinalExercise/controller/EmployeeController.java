package com.Springboot.Week4Day3FinalExercise.controller;

import com.Springboot.Week4Day3FinalExercise.Exception.RecordNotFoundException;
import com.Springboot.Week4Day3FinalExercise.model.Employee;
import com.Springboot.Week4Day3FinalExercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getEmployee(){
        List<Employee> employee = employeeService.findAllEmployee();
        return employee;
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        return employeeService.findEmployeeById(id);
    }
    @PostMapping("")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }



}
