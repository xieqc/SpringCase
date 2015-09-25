package com.xie.javacase.designPattern.behavior.visitor;

public interface Subject {
    public void accept(Visitor visitor);
    public String getSubject();
}
