package com.xie.springcase.mybatis.dao.impl;

import org.springframework.stereotype.Repository;

import com.xie.springcase.mybatis.dao.IEmployeeDAO;
import com.xie.springcase.mybatis.domain.Employee;

@Repository("BEmployeeDAO")
public class EmployeeDAO extends BaseDAO<Employee> implements IEmployeeDAO {
}
