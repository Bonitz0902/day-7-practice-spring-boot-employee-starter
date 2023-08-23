package com.thoughtworks.springbootemployee.company;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.employee.service.CompanyService;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void should_return_inactive_company_when_delete_given_company_service_and_company() {
        Long companyId = 1L;
        Company existingCompany = new Company(companyId, "Company A");
        existingCompany.setStatus(true);

        when(mockCompanyRepository.findById(companyId)).thenReturn(existingCompany);

        companyService.delete(companyId);

        verify(mockCompanyRepository).findById(companyId);
        verify(mockCompanyRepository).updateCompany(companyId, existingCompany); // Verify that the updateCompany method is called
        assertFalse(existingCompany.isActive());
    }

    @Test
    void should_return_list_of_companies() {
        List<Company> expectedCompanies = Arrays.asList(
                new Company(1L, "Company A"),
                new Company(2L, "Company B"),
                new Company(3L, "Company C")
        );

        when(mockCompanyRepository.listAll()).thenReturn(expectedCompanies);

        List<Company> returnedCompanies = companyService.listAll();

        assertEquals(expectedCompanies, returnedCompanies);
        verify(mockCompanyRepository).listAll(); // Verify that the repository method is called
    }
}
