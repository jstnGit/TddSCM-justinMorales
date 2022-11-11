package com.Springboot.Week4Day3FinalExercise.repository;

import com.Springboot.Week4Day3FinalExercise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
