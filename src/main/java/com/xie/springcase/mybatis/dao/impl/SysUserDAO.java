package com.xie.springcase.mybatis.dao.impl;

import com.xie.springcase.mybatis.dao.ISysUserDAO;
import com.xie.springcase.mybatis.domain.SysUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/6.
 */
@Repository("BSysUserDAO")
public class SysUserDAO extends BaseDAO<SysUser> implements ISysUserDAO {

}
