package com.xie.javacase.designPattern.create.builder;

/**
 * Created by xieqinchao on 15-9-21.
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
