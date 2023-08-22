package com.thoughtworks.springbootemployee.dataTransferObject;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Long id;
    private String name;
    private List<Employee> employeeList = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
