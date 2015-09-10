package com.xie.springcase.jpa.dao;

import com.xie.springcase.hibernate.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.QueryHint;

/**
 * Created by Xie on 2015/9/2.
 */
@Component("JSysUserDAO")
@RepositoryDefinition(domainClass = SysUser.class, idClass = Integer.class)
public interface SysUserDAO extends JpaRepository<SysUser,Integer> {
    @Query("select a from SysUser a where a.name=:name and a.password=:password")
//    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    SysUser authentication(@Param("name") String name, @Param("password") String password);

    SysUser findByName(String name);

    @Query("select a from SysUser a left join a.sysRoles where a.name=?1")
    SysUser fetchRoleByName(String name);
}
