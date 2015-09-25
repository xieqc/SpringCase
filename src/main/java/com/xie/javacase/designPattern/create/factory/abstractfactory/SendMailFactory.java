package com.xie.javacase.designPattern.create.factory.abstractfactory;

import com.xie.javacase.designPattern.create.factory.MailSender;
import com.xie.javacase.designPattern.create.factory.Sender;

public class SendMailFactory implements Provider {

    @Override
    public Sender produce(){
        return new MailSender();
    }
}
