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
//TODO: Formatted 1 line (ctrl alt L)
public class CompanyRepository {
    private final CompanyData companyData = new CompanyData();
    private final Map<Long, Company> companyMap = companyData.getCompanies();
    //TODO: remove extra lines

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
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toMap(
                        Company::getId,
                        company -> company
                ));
    }

    public Company save(Company company) {
        Long id = generateNextId();
        Company toBeSavedCompany = new Company(id, company.getName());
        companyMap.put(id, toBeSavedCompany);
        return toBeSavedCompany;
    }

    public Long generateNextId() {
        return companyMap.values().stream()
                .mapToLong(Company::getId)
                .max()
                .orElse(0L) + 1L;
    }

    public Company updateCompany(Long id,Company company) {
        Company matchedCompanyById = findById(id);

        matchedCompanyById.setName(company.getName());
        return matchedCompanyById;
    }

    public String deleteCompany(Long id) {
        companyMap.remove(id);
        return "Company Deleted";
    }
}



