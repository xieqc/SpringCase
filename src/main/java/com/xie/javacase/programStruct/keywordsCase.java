package com.xie.javacase.programStruct;

import java.io.Serializable;

/**
 * 关键词的使用实例
 */
public class keywordsCase {
    public static void main(String[] args) {
        MyClass my = new MyClass();
        if(my instanceof MyClass) {
            my.method2();
        } else {
            my.method1();
        }
        try {
            my.method3();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finish");
        }
    }
}

abstract class ParentClass {
    private boolean b = (Boolean)true;
    protected byte bt = 0;
    char c = (Character)'0';
    short s = 0;
    int i = (Integer)0;
    long l = (Long)0l;
    float f = (Float)0f;
    double d = (Double)0d;
    ParentClass() {
        System.out.println("ParentClass constructor");
        assert i==0;
    }
    public void method1() {
        System.out.println("method1");
    }
}

interface i1 {
    public void method2();
}

class MyClass extends ParentClass implements  i1,Serializable,Cloneable {
    MyClass() {
        System.out.println("MyClass constructor");
    }

    @Override
    public void method2() {
        super.method1();
        System.out.println("method2");
    }

    public void method3() throws Exception {
        System.out.println("method3");
        throw new Exception();
    }

    public MyClass clone() throws CloneNotSupportedException {
        return (MyClass)super.clone();
    }
}