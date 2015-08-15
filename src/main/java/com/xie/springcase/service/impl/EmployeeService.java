package com.xie.springcase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xie.springcase.hibernate.entity.Employee;
import com.xie.springcase.jpa.dao.EmployeeDAO;
import com.xie.springcase.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {
	@Resource(name="JEmployeeDAO")
	public EmployeeDAO employeeDAO;
	
	@Transactional(readOnly=true)
	public Employee save(Employee employee) {
		return employeeDAO.save(employee);
	}
}
