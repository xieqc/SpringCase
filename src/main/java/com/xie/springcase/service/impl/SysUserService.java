package com.xie.springcase.service.impl;

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

    @Transactional(readOnly = true)
    public SysUser findByName(String name) {
        return j_sysUserDAO.findByName(name);
    }

    @Transactional(readOnly = false)
    public SysUser save(SysUser user) {
        return j_sysUserDAO.save(user);
    }

    @Transactional(readOnly = true)
    public List<SysUser> findALL() {
        return j_sysUserDAO.findAll();
    }
}
