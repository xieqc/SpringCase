package com.xie.javacase.designPattern.create.factory;

public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mail sender!");
    }
}
