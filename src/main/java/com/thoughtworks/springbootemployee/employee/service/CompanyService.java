package com.thoughtworks.springbootemployee.employee.service;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public void delete(Long id) {
        Company findByIdCompany = companyRepository.findById(id);
        findByIdCompany.setStatus(false);
        updateCompany(findByIdCompany);
    }
    public void updateCompany(Company company) {
        company.setName(company.getName());
    }
}
