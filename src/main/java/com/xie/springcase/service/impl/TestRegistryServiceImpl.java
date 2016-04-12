package com.xie.springcase.service.impl;

import com.xie.springcase.service.TestRegistryService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/4/12.
 */
@Service("testRegistryService")
public class TestRegistryServiceImpl implements TestRegistryService {
    public String hello(String name) {
        return "hello"+name;
    }
}
