package com.thoughtworks.springbootemployee.staticData;
//TODO: Remove package name to match the regular expression
import com.thoughtworks.springbootemployee.dataTransferObject.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public List<Employee> employees(){
        final List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Alice", 30, "Female", 500));
        employees.add(new Employee(2L, "Bob", 23, "Male", 699));
        employees.add(new Employee(3L, "Sandra", 66, "Female", 788));
        employees.add(new Employee(4L, "Sam", 34, "Male", 4566));
        employees.add(new Employee(5L, "Aubrey", 69, "Male", 6969));

        return employees;
    }
    //TODO: Remove extra lines


}
