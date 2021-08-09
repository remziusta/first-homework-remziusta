package com.sms.model;

import javax.persistence.Entity;

@Entity
public class PermanentIntsructor extends Instructor{
    private Double salary;

    public PermanentIntsructor(Double salary) {
        this.salary = salary;
    }

    public PermanentIntsructor(String name, String address, Double salary) {
        super(name, address);
        this.salary = salary;
    }

    public PermanentIntsructor() {
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
