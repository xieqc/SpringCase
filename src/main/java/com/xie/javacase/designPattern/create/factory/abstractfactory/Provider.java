package com.xie.javacase.designPattern.create.factory.abstractfactory;

import com.xie.javacase.designPattern.create.factory.Sender;

public interface Provider {
    public Sender produce();
}
