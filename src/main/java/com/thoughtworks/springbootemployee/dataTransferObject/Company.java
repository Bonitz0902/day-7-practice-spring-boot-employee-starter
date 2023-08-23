package com.thoughtworks.springbootemployee.dataTransferObject;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Long id;
    private String name;
    private final List<Employee> employeeList = new ArrayList<>();
    private boolean isActive = true;

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

    public Company(String name) {
        this.name = name;
    }

    public Company() {

    }

    public void setStatus(boolean status) {
        this.isActive = status;
    }

    public Boolean isActive() {
        return isActive;
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void setId(long id) {
        this.id = id;
    }
}
