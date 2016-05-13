package com.xie.springcase.service;

import com.xie.springcase.hibernate.entity.Employee;

import java.util.List;

public interface IEmployeeService {

	public void h_save(Employee employee);

	public Employee j_save(Employee employee);

	void b_save(com.xie.springcase.mybatis.domain.Employee employee);

	com.xie.springcase.mybatis.domain.Employee b_findById(String id);

	List<String> getCacheTest();
}
