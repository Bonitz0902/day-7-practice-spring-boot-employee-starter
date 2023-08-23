package com.thoughtworks.springbootemployee.company;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.employee.service.CompanyService;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
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

    @Test
    void should_return_inactive_company_when_delete_given_company_service_and_company(){
        Company company = new Company(1L,"Company A");
        company.setId(1L);
        company.setStatus(true);

        when(mockCompanyRepository.findById(company.getId())).thenReturn(company);

        companyService.delete(company.getId());

        verify(mockCompanyRepository).updateCompany(eq(company.getId()), any(Company.class));
    }
}
