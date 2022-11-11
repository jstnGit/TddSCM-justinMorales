package com.Springboot.Week4Day3FinalExercise.service;

import com.Springboot.Week4Day3FinalExercise.Exception.RecordNotFoundException;
import com.Springboot.Week4Day3FinalExercise.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();
    Employee findEmployeeById (Long id)throws RecordNotFoundException;
    List<Employee> getAllEmployeesThatAreEarningMoreThan(double amount);
    List<Employee> getAllEmployeesExceedingAge(int age);
    List<Employee> getAllEmployeesWithMatchingPosition(String position);
    Employee getEmployeeWithHighestSalary();
    Employee saveEmployee(Employee employee);
}
