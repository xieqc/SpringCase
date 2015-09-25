package com.xie.javacase.designPattern.create.factory;

public class SendFactory {

    //1.普通工厂模式
    @Deprecated
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }


    //2.多个工厂方法模式 3.静态工厂方法模式
    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
