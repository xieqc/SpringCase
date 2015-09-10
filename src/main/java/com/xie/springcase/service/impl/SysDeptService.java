package com.xie.springcase.service.impl;

import com.xie.springcase.hibernate.entity.SysDept;
import com.xie.springcase.service.ISysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xieqinchao on 15-9-8.
 */
@Service
public class SysDeptService implements ISysDeptService {
    @Resource(name="JSysDeptDAO")
    private com.xie.springcase.jpa.dao.SysDeptDAO j_sysDeptDAO;

    @Transactional(readOnly = false)
    public SysDept j_save(SysDept dept) {
        return j_sysDeptDAO.save(dept);
    }

}
