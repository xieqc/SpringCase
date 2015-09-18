package com.xie.javacase.objectClass;

/**
 * 学生类（继承于抽象人类）
 */
public class Student extends Person {
    private String major;   //主修科目

    protected Student(String name) {
        super(name);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }
}
