package com.xie.springcase.jpa.dao;

import java.util.HashMap;
import java.util.List;

import com.xie.springcase.hibernate.entity.Employee;

public interface EmployeeDAOCustom {
	public List<Employee> getListByMap(HashMap map);
	
	public List<Employee> getCustomListByMap(HashMap map);
}
