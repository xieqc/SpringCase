package com.xie.javacase.designPattern.create.builder;

import com.xie.javacase.designPattern.create.factory.MailSender;
import com.xie.javacase.designPattern.create.factory.Sender;
import com.xie.javacase.designPattern.create.factory.SmsSender;

import java.util.ArrayList;
import java.util.List;

public class Builder {

    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }
}
