package com.thoughtworks.springbootemployee.employee;

import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.employee.service.EmployeeService;
import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.exception.EmployeeIsInactiveException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    private EmployeeRepository mockEmployeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        mockEmployeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(mockEmployeeRepository);
    }

    @Test
    void should_return_created_employee_when_create_given_employee_service_and_employee_with_valid_age() {

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
    void should_throw_exception_when_create_given_employee_service_and_employee_age_is_less_than_18() {
        Employee employee = new Employee("Pedo", 17, "Male", 999);

        EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, () -> {
            employeeService.create(employee);
        });

        assertEquals("Employee must be 18-65 years old", employeeCreateException.getMessage());

    }

    @Test
    void should_throw_exception_when_create_given_employee_service_and_employee_age_is_greater_than_65() {
        Employee employee = new Employee("Pedo", 100, "Male", 999);

        EmployeeCreateException employeeCreateException = assertThrows(EmployeeCreateException.class, () -> {
            employeeService.create(employee);
        });

        assertEquals("Employee must be 18-65 years old", employeeCreateException.getMessage());
    }

    @Test
    void should_return_inactive_employee_when_delete_given_employee_service_and_employee() {
        Employee employee = new Employee(1L, "Pedo", 55, "Male", 999);
        employee.setStatus(true);

        when(mockEmployeeRepository.findById(employee.getId())).thenReturn(employee);


        employeeService.delete(employee.getId());

        verify(mockEmployeeRepository).updateEmployee(eq(employee.getId()), argThat(tempEmployee -> {
            assertFalse(tempEmployee.isActive());
            assertEquals("Pedo", tempEmployee.getName());
            assertEquals(55, tempEmployee.getAge());
            assertEquals("Male", tempEmployee.getGender());
            assertEquals(999, tempEmployee.getSalary());
            return true;
        }));
    }

    @Test
    void should_throw_is_inactive_exception_when_update_given_employee_service_and_updated_employee() {
        Employee employee = new Employee(1L, "Guile", 20, "Male", 100000);
        employee.setStatus(false);

        when(mockEmployeeRepository.findById(employee.getId())).thenReturn(employee);

        EmployeeIsInactiveException employeeIsInactiveException =
                assertThrows(EmployeeIsInactiveException.class, () -> {
                    employeeService.update(employee.getId(), employee);
                });

        assertEquals("Employee is inactive", employeeIsInactiveException.getMessage());
    }

    @Test
    void should_return_all_employees_when_listAll_given_employee_service() {
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L, "Guile", 20, "Male", 123),
                new Employee(2L, "Miles", 19, "Male", 456),
                new Employee(3L, "Migule", 30, "Male", 789));

        when(mockEmployeeRepository.listAll()).thenReturn(employeeList);
        List<Employee> returnEmployeList = employeeService.listAll();

        verify(mockEmployeeRepository, times(1)).listAll();
        assertEquals(employeeList, returnEmployeList);
    }

    @Test
    void should_return_employee_when_findById_given_employee_service_employee_id() {
        Employee expectedEmployee = new Employee(1L, "Guile", 20, "Male", 123);

        when(mockEmployeeRepository.findById(1L)).thenReturn(expectedEmployee);

        Employee returnEmployee = employeeService.findById(1L);

        verify(mockEmployeeRepository, times(1)).findById(1L);

        assertEquals(expectedEmployee, returnEmployee);
    }

    @Test
    void should_return_gender_when_findByGender_given_employee_gender_employee_service() {
        String gender = "Male";
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1L, "Guile", 20, "Male", 123),
                new Employee(2L, "Miles", 19, "Male", 456),
                new Employee(3L, "Miguel", 30, "Male", 789));

        when(mockEmployeeRepository.findByGender(gender)).thenReturn(expectedEmployees);

        List<Employee> returnEmployee = employeeService.findByGender(gender);

        verify(mockEmployeeRepository, times(1)).findByGender(gender);

        returnEmployee.forEach(
                employee -> assertEquals(gender, employee.getGender())
        );
    }

    @Test
    void should_return_number_of_employee_in_page_when_findByPage_given_employee_service_pageSize_pageNumer() {
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1L, "Guile", 20, "Male", 123),
                new Employee(2L, "Miles", 19, "Male", 456));


        long pageSize = 2;
        long pageNumber = 2;

        when(mockEmployeeRepository.listByPage(pageNumber, pageSize)).thenReturn(expectedEmployees);

        List<Employee> returnEmployee = employeeService.listByPage(pageSize, pageNumber);

        verify(mockEmployeeRepository, times(1)).listByPage(pageNumber, pageSize);

        assertIterableEquals(expectedEmployees, returnEmployee);

    }


}
