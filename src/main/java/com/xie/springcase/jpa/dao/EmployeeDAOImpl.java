package com.xie.springcase.jpa.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.xie.springcase.hibernate.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAOCustom {
	@Resource
	private EntityManager entityManager;
	
	public List<Employee> getListByMap(HashMap map) {
		StringBuilder sql = new StringBuilder("select * from employee");
		Query query = entityManager.createNativeQuery(sql.toString(), Employee.class);
		List<Employee> list = (List<Employee>)query.getResultList();
		return list;
	}
	
	public List<Employee> getCustomListByMap(HashMap map) {
		StringBuilder sql = new StringBuilder("select * from employee");
		Query query = entityManager.createNativeQuery(sql.toString(), Employee.class);
		List<Employee> list = (List<Employee>)query.getResultList();
		return list;
	}
}
