package com.thoughtworks.springbootemployee.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApiTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvcClient;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void cleanupEmployeeData(){
        employeeRepository.cleanAll();
    }

    @Test
    void should_return_all_given_employee_when_perform_get_employee() throws Exception {
        Employee miles = employeeRepository.save(new Employee(1L, "Mile", 25, "Male", 9000));

        mockMvcClient.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(miles.getId()))
                .andExpect(jsonPath("$[0].name").value(miles.getName()))
                .andExpect(jsonPath("$[0].age").value(miles.getAge()))
                .andExpect(jsonPath("$[0].gender").value(miles.getGender()))
                .andExpect(jsonPath("$[0].salary").value(miles.getSalary()));
    }

    @Test
    void should_return_the_employee_when_perform_get_employee_given_a_employee_id() throws Exception{
        Employee employee = new Employee("John Doe", 30, "Male", 50000);
        employeeRepository.save(employee);

        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()));


    }

    @Test
    void should_return_404_not_found_when_perform_get_employee_given_a_not_existed_id()throws Exception{
        long notExistEmployeeId = 99L;

        mockMvcClient.perform(get("/employees" + notExistEmployeeId))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_the_employee_by_given_gender_when_perform_get_employees() throws Exception{
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", 30, "Male", 50000),
                new Employee("Jane Smith", 28, "Female", 60000));



        //when(employeeService.findByGender("Male")).thenReturn(expectedEmployees);

        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees")
                        .param("gender", "Male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(employees.size()));

    }

    @Test
    void should_return_the_employee_created_when_perform_post_employee_given_a_new_employee_with_JSON_format() throws Exception{

        Employee expectedEmployee = new Employee("Miles", 25, "Male", 9000);

        mockMvcClient.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expectedEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(expectedEmployee.getName()))
                .andExpect(jsonPath("$.age").value(expectedEmployee.getAge()))
                .andExpect(jsonPath("$.gender").value(expectedEmployee.getGender()))
                .andExpect(jsonPath("$.salary").value(expectedEmployee.getSalary()));


        //verify(employeeService).create(any(Employee.class));

    }

    @Test
    void should_return_updated_employee_when_perform_put_employee_given_a_updated_employee() throws Exception{
        Employee existingEmployee = new Employee(1L, "John Doe", 30, "Male", 50000);
        employeeRepository.save(existingEmployee);

        Employee updatedEmployee = new Employee(1L, "John Doe", 35, "Male", 55000);

        mockMvcClient.perform(MockMvcRequestBuilders.put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void should_delete_employee_when_perform_delete_given_employee_id() throws Exception{
        Employee miles = employeeRepository.save(new Employee(1L,"Miles",25,"Male",9000));

        mockMvcClient.perform(MockMvcRequestBuilders.delete("/employees/"+miles.getId()))
                .andExpect(status().isNoContent());
    }
}

