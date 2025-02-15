package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dataTransferObject.Company;
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.employee.service.CompanyService;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> listAll() {
        return companyService.listAll();
    }

    @GetMapping(path = "/{id}")
    public Company findById(@PathVariable Long id) {
        return companyRepository.findById(id);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> listAllCompanyEmployees(@PathVariable Long id) {
        return companyRepository.findEmployees(id);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public Map<Long, Company> listByPage(@RequestParam Long pageNumber,
                                         @RequestParam Long pageSize) {
        return companyRepository.listByPage(pageNumber, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping(path = "/{id}")
    public Company updateCompany(@RequestParam Long id, @RequestBody Company company) {
        return companyRepository.updateCompany(id, company);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCompany(@PathVariable Long id) {
        return companyRepository.deleteCompany(id);
    }
}
