package com.xie.javacase.objectClass;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 雇员类（继承于抽象人类）
 */
public class Employee extends Person {
    private double salary;  //薪资
    private Date hireDay;   //入职日期

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
        hireDay = calendar.getTime();
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $%.2f", salary);
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
