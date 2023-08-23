package com.thoughtworks.springbootemployee.company;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.employee.service.CompanyService;
import com.thoughtworks.springbootemployee.employee.service.EmployeeService;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    private CompanyRepository mockCompanyRepository;
    private CompanyService companyService;

    @BeforeEach
    void setup() {
        mockCompanyRepository = mock(CompanyRepository.class);
        companyService = new CompanyService(mockCompanyRepository);
    }

    @Test
    void should_return_created_company_when_create_given_company_service_and_company() {
        Company company = new Company("Company A");
        Company savedCompany = new Company(1L, "Company A");

        when(mockCompanyRepository.save(company)).thenReturn(savedCompany);

        Company returnCompany = companyService.create(company);

        assertEquals(1L, returnCompany.getId());
        assertEquals("Company A", returnCompany.getName());
    }
}
