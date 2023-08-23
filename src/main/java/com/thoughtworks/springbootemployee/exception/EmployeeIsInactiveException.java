package com.thoughtworks.springbootemployee.exception;

public class EmployeeIsInactiveException extends RuntimeException{

    public EmployeeIsInactiveException(){
        super("Employee is inactive");
    }
}
