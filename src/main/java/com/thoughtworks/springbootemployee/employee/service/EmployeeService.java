package com.thoughtworks.springbootemployee.employee.service;

import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeCreateException;
import com.thoughtworks.springbootemployee.exception.EmployeeIsInactiveException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        if (employee.hasInvalidAge()) {
            throw new EmployeeCreateException();
        }
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        Employee findByIdEmployee = employeeRepository.findById(id);
        findByIdEmployee.setStatus(false);
        employeeRepository.updateEmployee(id, findByIdEmployee);
    }

    public void update(Long id, Employee employee) {
        Employee findByIdEmployee = employeeRepository.findById(id);
        if (!findByIdEmployee.isActive()) {
            throw new EmployeeIsInactiveException();
        } else {
            employeeRepository.updateEmployee(id, employee);
        }

    }


    public List<Employee> listAll() {
        return employeeRepository.listAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id);

    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);

    }

    public List<Employee> listByPage(Long pageNumber, Long pageSize) {
        return employeeRepository.listByPage(pageNumber,pageSize);

    }
}
