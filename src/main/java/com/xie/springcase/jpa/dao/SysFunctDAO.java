package com.xie.springcase.jpa.dao;

import com.xie.springcase.hibernate.entity.SysFunct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Xie on 2015/9/2.
 */
@Component("JSysFunctDAO")
@RepositoryDefinition(domainClass = SysFunct.class, idClass = Integer.class)
public interface SysFunctDAO extends JpaRepository<SysFunct,Integer> {

    @Query(value="select distinct f.name from sys_funct f,sys_role r,sys_user u,cfg_role_funct rf,cfg_user_role ur " +
            "where rf.funct_id=f.id and rf.role_id=r.id and ur.role_id=r.id and ur.user_id=u.id and u.id=?1", nativeQuery = true)
    List<String> getFunctNameListBySysUserId(Integer userId);
}
