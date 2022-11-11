package com.Springboot.Week4Day3FinalExercise.service;

import com.Springboot.Week4Day3FinalExercise.Exception.RecordNotFoundException;
import com.Springboot.Week4Day3FinalExercise.model.Employee;
import com.Springboot.Week4Day3FinalExercise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id)throws RecordNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmployeesThatAreEarningMoreThan(double amount) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getSalary() > amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmployeesExceedingAge(int age) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getAge() > age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmployeesWithMatchingPosition(String position) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeWithHighestSalary() {
        return employeeRepository.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getSalary)).get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
