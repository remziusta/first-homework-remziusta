package com.sms.model;

import javax.persistence.Entity;

@Entity
public class VisitorInstructor extends Instructor{
    private Double salary;

    public VisitorInstructor(Double salary) {
        this.salary = salary;
    }

    public VisitorInstructor(String name, String address, Double salary) {
        super(name, address);
        this.salary = salary;
    }

    public VisitorInstructor() {}

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
