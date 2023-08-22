package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.staticData.CompanyData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository

public class CompanyRepository {
    private CompanyData companyData = new CompanyData();
    private Map<Long, Company> companyMap = companyData.getCompanies();
    private Employee employee = new Employee(2L,"123123",23,"232",23);


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
}
