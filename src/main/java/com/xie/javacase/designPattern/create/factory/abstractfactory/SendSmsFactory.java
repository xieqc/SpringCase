package com.xie.javacase.designPattern.create.factory.abstractfactory;

import com.xie.javacase.designPattern.create.factory.Sender;
import com.xie.javacase.designPattern.create.factory.SmsSender;

public class SendSmsFactory implements Provider{

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
