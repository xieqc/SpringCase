package com.xie.javacase.designPattern.structure.proxy;

public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
