package com.xie.javacase.designPattern.structure.bridge;

public class MyBridge extends Bridge {
    public void method(){
        getSource().method();
    }
}
