package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.staticData.CompanyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository

public class CompanyRepository {
    private CompanyData companyData = new CompanyData();
    private Map<Long, Company> companyMap = companyData.getCompanies();
    private Employee employee = new Employee(2L,"123123",23,"232",23);


    public List<Company> listAll() {
        return new ArrayList<>(companyMap.values());
    }
}
