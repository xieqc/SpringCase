package com.xie.springcase.mybatis.dao.impl;

import com.xie.springcase.mybatis.dao.ISysDeptDAO;
import com.xie.springcase.mybatis.domain.SysDept;
import com.xie.springcase.mybatis.mapper.SysDeptMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/11.
 */
@Repository("BSysDeptDAO")
public class SysDeptDAO implements ISysDeptDAO {
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public int insert(SysDept dept) {
        return sysDeptMapper.insert(dept);
    }
}
