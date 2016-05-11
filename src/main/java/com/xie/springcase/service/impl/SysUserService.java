package com.xie.springcase.service.impl;

import com.xie.springcase.annotation.DataLog;
import com.xie.springcase.hibernate.entity.SysUser;
import com.xie.springcase.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
@Service
public class SysUserService implements ISysUserService {
    @Resource(name="JSysUserDAO")
    public com.xie.springcase.jpa.dao.SysUserDAO j_sysUserDAO;
    @Resource(name="BSysUserDAO")
    public com.xie.springcase.mybatis.dao.ISysUserDAO b_sysUserDAO;

    @DataLog(type = "login", description = "登录")
    @Transactional(readOnly = true)
    public SysUser findByName(String name) {
        return j_sysUserDAO.findByName(name);
    }

    @DataLog(type = "save", description = "保存")
    @Transactional(readOnly = false)
    public SysUser save(SysUser user) {
        return j_sysUserDAO.save(user);
    }

    @Transactional(readOnly = false)
    public void b_save(com.xie.springcase.mybatis.domain.SysUser user) {
        b_sysUserDAO.insert(user);
    }

    @DataLog(type = "query", description = "查询")
    @Transactional(readOnly = true)
    public List<SysUser> findALL() {
        return j_sysUserDAO.findAll();
    }
}
