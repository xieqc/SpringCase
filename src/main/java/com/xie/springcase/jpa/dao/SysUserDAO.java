package com.xie.springcase.jpa.dao;

import com.xie.springcase.hibernate.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by Xie on 2015/9/2.
 */
@Component("JSysUserDAO")
@RepositoryDefinition(domainClass = SysUser.class, idClass = Integer.class)
public interface SysUserDAO extends JpaRepository<SysUser,Integer> {
}
