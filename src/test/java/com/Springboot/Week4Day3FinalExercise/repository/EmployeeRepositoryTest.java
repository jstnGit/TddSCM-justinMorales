package com.Springboot.Week4Day3FinalExercise.repository;

import com.Springboot.Week4Day3FinalExercise.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    Employee test1 = new Employee(1L, "test1", 23, 30000d, "SE");
    Employee test2 = new Employee(2L, "test2", 30, 200000d, "SE");
    Employee test3 = new Employee(3L, "test3", 22, 2000000d, "SE");
    Employee testSave = new Employee(4L, "testSave", 24, 2000000d, "SE");
    List<Employee> employees = List.of(test1
            , test2, test3);

    @Test
    @DisplayName("Test Repository FindAll")
    void testFindAll() {
        //arrange
        List<Employee> findEmployee=employeeRepository.saveAll(employees);
        //act

        //assert
        assertThat(employeeRepository.findAll()).containsAll(findEmployee);
    }

    @Test
    @DisplayName("Test Repository FindById Positive Output")
    void testPositiveFindById() {
        //arrange
        employeeRepository.saveAll(employees);
        Optional<Employee> findEmployeeById=employeeRepository.findById(1L);
        //act

        //assert
        assertEquals(findEmployeeById, employeeRepository.findById(1L));
    }

    @Test
    @DisplayName("Test Repository save and if the Id is working" +
                "WHEN save is Executed with ID value=4" +
                "THEN result should return 4")
    void testSave() {
        //arrange
        Employee savedEmployee = employeeRepository.save(testSave);
        //act

        //assert

        assertNotNull(savedEmployee);
        assertEquals(4L,savedEmployee.getId());
    }
}
