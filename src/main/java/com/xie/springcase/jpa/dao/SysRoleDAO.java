package com.xie.springcase.jpa.dao;

import com.xie.springcase.hibernate.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Xie on 2015/9/2.
 */
@Component("JSysRoleDAO")
@RepositoryDefinition(domainClass = SysRole.class, idClass = Integer.class)
public interface SysRoleDAO extends JpaRepository<SysRole,Integer> {

    @Query(value="select r.name from sys_role r,sys_user u,cfg_user_role c where c.role_id=r.id and c.user_id=u.id and u.id=?1", nativeQuery = true)
    List<String> getRoleNameListBySysUserId(Integer userId);
}
