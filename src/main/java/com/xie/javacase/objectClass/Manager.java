package com.xie.javacase.objectClass;

/**
 * 经理类（继承于雇员类）
 */
public class Manager extends Employee {
    private double bonus; //奖金

    public Manager(String name, double salary, int year, int month, int day, double bonus) {
        super(name, salary, year, month, day);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
