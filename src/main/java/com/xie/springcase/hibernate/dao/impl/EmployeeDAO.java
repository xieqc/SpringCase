package com.xie.springcase.hibernate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xie.springcase.hibernate.dao.IEmployeeDAO;
import com.xie.springcase.hibernate.entity.Employee;

@Repository("HEmployeeDAO")
public class EmployeeDAO extends BaseDAO<Employee> implements IEmployeeDAO {

	protected EmployeeDAO() {
		super(new Employee());
	}

	public List<Employee> getAll() {
		return (List<Employee>) this.getHibernateTemplate().find("from Employee where 1=1");
	}
}
