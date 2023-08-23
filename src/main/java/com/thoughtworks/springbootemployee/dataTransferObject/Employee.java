package com.thoughtworks.springbootemployee.dataTransferObject;

public class Employee {
    //TODO: You can use wrapper class for this part (default value is null for wrapper class which fits the business intent)
    private long id;
    private String name;
    private int age;
    private String gender;
    private int salary;

    public Employee(Long id, String name, int age, String gender, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee( String name, int age, String gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } //TODO: remove unused method

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } //TODO: remove unused method

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    } //TODO: remove unused method

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
