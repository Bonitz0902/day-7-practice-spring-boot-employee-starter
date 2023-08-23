package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: Formatted 4 lines (ctrl alt L)
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> listAll() {
        return employeeRepository.listAll();
    }

    @GetMapping(path = "/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> findByGender(@RequestParam String gender) {
        return employeeRepository.findByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(path = "/{id}") //TODO: Include the id as parameter as PathVariable as first parameter
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteEmployee(@PathVariable Long id) {
        return employeeRepository.deleteEmployee(id);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public List<Employee> listByPage(@RequestParam Long pageNumber,
                                     @RequestParam Long pageSize) {
        return employeeRepository.listByPage(pageNumber, pageSize);
    }
}
