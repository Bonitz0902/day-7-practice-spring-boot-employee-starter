package com.thoughtworks.springbootemployee.staticData;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;

import java.util.HashMap;
import java.util.Map;

public class CompanyData {
    public Map<Long, Company> getCompanies() {

        Map<Long, Company> companyMap = new HashMap<>();

        Company lazadaCompany = new Company(1L, "Lazada");

        lazadaCompany.addEmployee(new Employee(1L, "Miguel",45,"Male", 2000));
        lazadaCompany.addEmployee(new Employee(2L, "Gwen", 20,"Female",200));

        Company shopeeCompany = new Company(2L, "Shopee");

        shopeeCompany.addEmployee(new Employee(1L,"Miles", 18,"Male",300));
        shopeeCompany.addEmployee(new Employee(2L, "Toby", 40, "Male", 3000));

        companyMap.put(1L, lazadaCompany);
        companyMap.put(2L, shopeeCompany);

        return companyMap;
    }
}
