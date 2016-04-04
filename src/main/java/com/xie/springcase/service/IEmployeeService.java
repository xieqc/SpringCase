package com.xie.springcase.service;

import com.xie.springcase.hibernate.entity.Employee;

public interface IEmployeeService {

	public void h_save(Employee employee);

	public Employee j_save(Employee employee);

	void b_save(com.xie.springcase.mybatis.domain.Employee employee);
	
}
