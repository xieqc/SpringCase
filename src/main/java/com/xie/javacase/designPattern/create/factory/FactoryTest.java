package com.xie.javacase.designPattern.create.factory;

import com.xie.javacase.designPattern.create.factory.abstractfactory.Provider;
import com.xie.javacase.designPattern.create.factory.abstractfactory.SendMailFactory;

public class FactoryTest {

    public static void main(String[] args) {
        //1.普通工厂模式
//        Sender sender = (new SendFactory()).produce("mail");

        //2.多个工厂方法模式 3.静态工厂方法模式
//        Sender sender = SendFactory.produceMail();

        //抽象工厂方法
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();

        sender.Send();
    }
}
