package com.xie.javacase.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-11-19
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
class GroovyTest implements IGroovyTest {
    @Override
    void SayHelloWorld() {
        printf("Hello World!");
    }

    @Override
    Object doit(Object obj) {
        printf("Hello World!");
        obj * 10;
    }

    public static void main(String[] args) {
        printf("Hello World!");
    }
}
