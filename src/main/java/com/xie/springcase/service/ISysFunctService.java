package com.xie.springcase.service;

import com.xie.springcase.hibernate.entity.SysFunct;

import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
public interface ISysFunctService {

    SysFunct save(SysFunct funct);

    List<String> getFunctNameListBySysUserId(Integer userId);

}
