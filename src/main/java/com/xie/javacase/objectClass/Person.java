package com.xie.javacase.objectClass;

/**
 * 人类（抽象类）
 */
public abstract class Person {
    private String name;

    protected Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getDescription();
}
