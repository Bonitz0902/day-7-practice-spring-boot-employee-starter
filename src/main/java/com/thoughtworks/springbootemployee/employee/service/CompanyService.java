package com.thoughtworks.springbootemployee.employee.service;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> listAll() {
        return null;
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }
}
