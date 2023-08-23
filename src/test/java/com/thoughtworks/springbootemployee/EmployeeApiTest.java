package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApiTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvcClient;

    @BeforeEach
    void cleanupEmployeeData(){
        employeeRepository.cleanAll();
    }

    @Test
    void should_return_all_given_employee_when_perform_get_employee() throws Exception {
        Employee miles = employeeRepository.save(new Employee(1L,"Mile",25,"Male",9000));

        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees"))
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
        Employee miles = employeeRepository.save(new Employee(1L,"Miles",25,"Male",9000));
        employeeRepository.save(new Employee(2L,"Bob", 28, "Male",6000));

        mockMvcClient.perform(MockMvcRequestBuilders.get("/employees/" + miles.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(miles.getId()))
                .andExpect(jsonPath("$.name").value(miles.getName()))
                .andExpect(jsonPath("$.age").value(miles.getAge()))
                .andExpect(jsonPath("$.gender").value(miles.getGender()))
                .andExpect(jsonPath("$.salary").value(miles.getSalary()));


    }



}

