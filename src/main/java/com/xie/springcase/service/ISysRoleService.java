package com.xie.springcase.service;

import com.xie.springcase.hibernate.entity.SysRole;

import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
public interface ISysRoleService {

    SysRole save(SysRole role);

    List<String> getRoleNameListBySysUserId(Integer userId);
}
