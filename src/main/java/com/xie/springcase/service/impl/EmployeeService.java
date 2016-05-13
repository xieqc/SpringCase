package com.xie.springcase.service.impl;

import javax.annotation.Resource;

import com.xie.springcase.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xie.springcase.hibernate.entity.Employee;
import com.xie.springcase.service.IEmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
	@Resource(name="HEmployeeDAO")
	public com.xie.springcase.hibernate.dao.IEmployeeDAO h_employeeDAO;
	@Resource(name="JEmployeeDAO")
	public com.xie.springcase.jpa.dao.EmployeeDAO j_employeeDAO;
	@Resource(name="BEmployeeDAO")
	public com.xie.springcase.mybatis.dao.IEmployeeDAO b_employeeDAO;
	
	@Transactional(readOnly=false)
	public void h_save(Employee employee) {
		h_employeeDAO.save(employee);
	}

	@Transactional(readOnly=false)
	public Employee j_save(Employee employee) {
		return j_employeeDAO.save(employee);
	}

	@Transactional(readOnly=false)
	public void b_save(com.xie.springcase.mybatis.domain.Employee employee) {
		b_employeeDAO.insert(employee);
	}

	public com.xie.springcase.mybatis.domain.Employee b_findById(String id) {
		return b_employeeDAO.selectById(id);
	}

	@CacheConfig(useCache = true,cacheName = "demoCache",keyArgsIndex = "0")
	public List<String> getCacheTest() {
		List<String> strList = new ArrayList<String>();
		strList.add("test1");
		strList.add("test2");
		return strList;
	}
}
