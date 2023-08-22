package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dataTransferObject.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepository {
    private static final List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1L, "Alice", 30, "Female", 500));
        employees.add(new Employee(2L, "Bob", 23, "Male", 699));
        employees.add(new Employee(3L, "Sandra", 66, "Female", 788));
        employees.add(new Employee(4L, "Sam", 34, "Male", 4566));
        employees.add(new Employee(5L, "Aubrey", 69, "Male", 6969));

    }

    public List<Employee> listAll() {
        return employees;
    }

    public Employee findById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee save(Employee employee){
        Long id = generateNextId();
        Employee toBeSavedEmployee = new Employee(id, employee.getName(),employee.getAge(),employee.getGender(),employee.getSalary());
        employees.add(toBeSavedEmployee);
        return toBeSavedEmployee;
    }

    public Long generateNextId(){
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1L;
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        Employee matchedEmployeeById = findById(updatedEmployee.getId());

        matchedEmployeeById.setAge(updatedEmployee.getAge());
        matchedEmployeeById.setSalary(updatedEmployee.getSalary());

        return matchedEmployeeById;
    }



    public String deleteEmployee(Long employeeId) {
         Employee matchedEmployeeById = findById(employeeId);
         employees.remove(matchedEmployeeById);
         return "Employee Deleted.";
    }

    public List<Employee> listByPage(Long pageNumber, Long pageSize) {
        return employees.stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
