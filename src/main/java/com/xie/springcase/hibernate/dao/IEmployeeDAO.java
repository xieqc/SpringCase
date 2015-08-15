package com.xie.springcase.hibernate.dao;

import java.util.List;

import com.xie.springcase.hibernate.entity.Employee;

public interface IEmployeeDAO extends IBaseDAO<Employee> {
	
	public List<Employee> getAll();
	
}
