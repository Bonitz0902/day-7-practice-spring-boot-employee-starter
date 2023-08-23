package com.thoughtworks.springbootemployee.dataTransferObject;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Long id; //TODO: Can be changed to  final
    private String name;
    private List<Employee> employeeList = new ArrayList<>(); //TODO: Can be changed to  final

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }
}
