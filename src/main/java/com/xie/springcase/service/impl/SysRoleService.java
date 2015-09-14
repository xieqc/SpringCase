package com.xie.springcase.service.impl;

import com.xie.springcase.hibernate.entity.SysRole;
import com.xie.springcase.jpa.dao.SysRoleDAO;
import com.xie.springcase.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xieqinchao on 15-9-8.
 */
@Service
public class SysRoleService implements ISysRoleService {
    @Resource(name="JSysRoleDAO")
    private SysRoleDAO j_sysRoleDAO;

    @Transactional(readOnly = false)
    public SysRole save(SysRole role) {
        return j_sysRoleDAO.save(role);
    }

    @Transactional(readOnly = true)
    public List<String> getRoleNameListBySysUserId(Integer userId) {
        return j_sysRoleDAO.getRoleNameListBySysUserId(userId);
    }

}
