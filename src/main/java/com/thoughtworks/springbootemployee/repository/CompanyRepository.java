package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.staticData.CompanyData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository

public class CompanyRepository {
    private CompanyData companyData = new CompanyData();
    private Map<Long, Company> companyMap = companyData.getCompanies();


    public List<Company> listAll() {
        return new ArrayList<>(companyMap.values());
    }

    public Company findById(Long id) {
        return companyMap.get(id);
    }

    public List<Employee> findEmployees(Long id) {
        Company company = findById(id);
        return company.getEmployeeList();
    }

    public Map<Long, Company> listByPage(Long pageNumber, Long pageSize) {
        return companyMap.values().stream()
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toMap(
                        Company::getId,
                        company -> company
                ));
    }

    public Company save(Company company) {
        Long id = generateNextId();
        Company toBeSavedCompany = new Company(id, company.getName());
        companyMap.put(toBeSavedCompany.getId(), company);
        return toBeSavedCompany;
    }

    public Long generateNextId() {
        return companyMap.values().stream()
                .mapToLong(Company::getId)
                .max()
                .orElse(0L) + 1L;
    }

    public Company updateCompany(Company company) {
        Company matchedCompanyById = findById(company.getId());

        matchedCompanyById.setName(company.getName());
        return matchedCompanyById;
    }

    public String deleteCompany(Long id) {
        companyMap.remove(id);
        return "Company Deleted";
    }
}



