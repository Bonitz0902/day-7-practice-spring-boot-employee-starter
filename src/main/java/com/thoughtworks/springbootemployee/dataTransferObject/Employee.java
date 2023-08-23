package com.thoughtworks.springbootemployee.dataTransferObject;

public class Employee {
    private long id;
    private String name;
    private int age;
    private String gender;
    private int salary;
    private boolean isActive = true;
    private static final int MIN_VALID_AGE = 18;
    private static final int MAX_VALID_AGE = 65;

    public Employee(Long id, String name, int age, String gender, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee() {

    }

    public Employee(String name, int age, String gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean hasInvalidAge() {
        return getAge() < MIN_VALID_AGE || getAge() > MAX_VALID_AGE;
    }

    public void setStatus(boolean status){
        this.isActive = status;
    }

    public Boolean isActive() {
        return isActive;
    }

}
