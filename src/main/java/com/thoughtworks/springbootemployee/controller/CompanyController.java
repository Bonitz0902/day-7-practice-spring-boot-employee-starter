package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {


    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> listAll() {
        return companyRepository.listAll();
    }

    @GetMapping(path = "/{id}")
    public Company findById(@PathVariable Long id){
        return companyRepository.findById(id);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> listAllCompanyEmployees(@PathVariable Long id){
        return companyRepository.findEmployees(id);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public Map<Long, Company> listByPage(@RequestParam Long pageNumber,
                                         @RequestParam Long pageSize){
        return companyRepository.listByPage(pageNumber,pageSize);
    }


}
