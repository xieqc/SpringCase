package com.xie.springcase.jpa.dao;

import com.xie.springcase.hibernate.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by Xie on 2015/9/2.
 */
@Component("JSysDeptDAO")
@RepositoryDefinition(domainClass = SysDept.class, idClass = Integer.class)
public interface SysDeptDAO extends JpaRepository<SysDept,Integer> {
}
