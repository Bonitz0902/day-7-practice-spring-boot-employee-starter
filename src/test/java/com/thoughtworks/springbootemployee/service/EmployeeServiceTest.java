package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.EmployeeService;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    private EmployeeRepository mockEmployeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        mockEmployeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(mockEmployeeRepository);
    }

    @Test
    void should_return_created_employee_when_create_given_employee_service_and_employee_with_valid_age(){

        Employee employee = new Employee("Guile", 20, "Male", 100000);
        Employee savedEmployee = new Employee(1L, "Guile", 20, "Male", 100000);

        when(mockEmployeeRepository.save(employee)).thenReturn(savedEmployee);

        Employee employeeResponse = employeeService.create(employee);

        assertEquals(savedEmployee.getId(), employeeResponse.getId());
        assertEquals("Guile", employeeResponse.getName());
        assertEquals(20, employeeResponse.getAge());
        assertEquals("Male", employeeResponse.getGender());
        assertEquals(100000, employeeResponse.getSalary());

    }

    @Test
    void should_throw_exception_when_create_given_employee_service_and_employee_age_is_less_than_18(){
        Employee employee = new Employee("Pedo", 17, "Male", 999);

        EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, ()-> {
            employeeService.create(employee);
        });

        assertEquals("Employee must be 18-65 years old", employeeCreateException.getMessage());

    }
}
