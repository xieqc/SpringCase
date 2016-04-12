package com.xie.springcase.test;

import com.xie.springcase.service.TestRegistryService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ClientTest {
    public static ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext_client.xml"});
    public static TestRegistryService testRegistryService = (TestRegistryService) ac.getBean("testRegistryService");

    @Test
    public void dubboTest() {
        System.out.println("\n"+testRegistryService.hello(" world"));
    }
}
