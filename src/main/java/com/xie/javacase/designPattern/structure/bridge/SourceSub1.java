package com.xie.javacase.designPattern.structure.bridge;

public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
