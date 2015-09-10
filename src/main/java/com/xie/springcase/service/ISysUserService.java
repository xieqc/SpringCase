package com.xie.springcase.service;

import com.xie.springcase.hibernate.entity.SysUser;

import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
public interface ISysUserService {

    SysUser findByName(String name);

    SysUser save(SysUser user);

    List<SysUser> findALL();
}
