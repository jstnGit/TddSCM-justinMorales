package com.Springboot.Week4Day3FinalExercise.service;

import com.Springboot.Week4Day3FinalExercise.Exception.RecordNotFoundException;
import com.Springboot.Week4Day3FinalExercise.model.Employee;
import com.Springboot.Week4Day3FinalExercise.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    Employee test1 = new Employee(1L, "test1", 23, 30000d, "SE");
    Employee test2 = new Employee(2L, "test2", 30, 200000d, "SE");
    Employee test3 = new Employee(3L, "test3", 22, 2000000d, "SE");
    List<Employee> employees = List.of(test1
            , test2, test3);
    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getAllEmployees is executed " +
            "THEN result should return test1,test2 and test3")
    public void testFindAll () {

        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);
        //act
        List<Employee> filteredEmployees = employeeServiceImpl.findAllEmployee();

        //assert
        assertThat(filteredEmployees).contains(test1, test2,test3);
    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getEmployee is executed with input 1" +
            "THEN result should return test1")
    public void testPositiveFindById () throws RecordNotFoundException {
        //arrange
        Long id=1L;
        Mockito.when(employeeRepository.findById(id))
                .thenReturn(Optional.ofNullable(test1));
        //Optional<Employee> expectedEmployee = employeeRepository.findById(id);
        //act
        Employee filteredEmployees = employeeServiceImpl.findEmployeeById(id);
        //assert
        assertEquals(test1,filteredEmployees);
    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getEmployee is executed with input 51" +
            "THEN result should return RecordNotFoundException")
    public void testNegativeFindById () {
        //arrange
        Long id=51L;
        //act

        //assert
        assertThrows(RecordNotFoundException.class, () -> employeeServiceImpl.findEmployeeById(id));
    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getAllEmployeesThatAreEarningMoreThan is executed with input 90000" +
            "THEN result should return test2 and test3")
    public void testUpdateEmployee () {
        //arrange
        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);
        //act
        List<Employee> filteredEmployees = employeeServiceImpl.getAllEmployeesThatAreEarningMoreThan(90000d);
        //assert
        assertThat(filteredEmployees).contains(test2,test3);
    }
    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getEmployeeThatHasAnExceedingAge, executed with input 25" +
            "THEN result should return wayne")
    public void testEmployeesExceedingAge() {
        //Running... setup()
        //arrange
        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);
        //act
        List<Employee> filteredEmployees = employeeServiceImpl.getAllEmployeesExceedingAge(25);

        //assert
        assertThat(filteredEmployees).contains(test2);

    }

    @Test
    @DisplayName("GIVEN employees from SQL in data.sql " +
            "WHEN getEmployeeThatHasMatchingPosition, executed with input SE" +
            "THEN result should return test1,test2 and test3")
    public void testEmployeesWithMatchingPosition() {
        //Running... setup()
        //arrange
        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);
        //act
        List<Employee> filteredEmployees = employeeServiceImpl.getAllEmployeesWithMatchingPosition("SE");

        //assert
        assertThat(filteredEmployees).contains(test1,test2,test3);

    }

    @Test
    @DisplayName("GIVEN employees from repository with the setup above " +
            "WHEN getEmployeeThatHasHighestSalary" +
            "THEN result should return alejandro")
    public void testEmployeesWithHighestSalary() {
        //Running... setup()
        //arrange
        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);
        //act
        Employee filteredEmployees = employeeServiceImpl.getEmployeeWithHighestSalary
                ();

        //assert
        assertEquals(test3,filteredEmployees);

    }
}
