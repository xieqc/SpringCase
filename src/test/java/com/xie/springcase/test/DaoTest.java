package com.xie.springcase.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.xie.springcase.hibernate.entity.Employee;
import com.xie.springcase.jpa.util.NativeSql;
import com.xie.springcase.service.IEmployeeService;

public class DaoTest {
	public static ApplicationContext ac =new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
	public static com.xie.springcase.hibernate.dao.IEmployeeDAO h_employeeDAO = (com.xie.springcase.hibernate.dao.IEmployeeDAO)ac.getBean("HEmployeeDAO");
	public static com.xie.springcase.mybatis.dao.IEmployeeDAO b_employeeDAO = (com.xie.springcase.mybatis.dao.IEmployeeDAO)ac.getBean("BEmployeeDAO");
	public static com.xie.springcase.jpa.dao.EmployeeDAO j_employeeDao = (com.xie.springcase.jpa.dao.EmployeeDAO)ac.getBean("JEmployeeDAO");
	public static IEmployeeService employeeService = (IEmployeeService)ac.getBean("employeeService");
	
//	@Test
	public void hibernateTest() {
        /* insert */
        Employee employee = new Employee();
        employee.setId("100007");
        employee.setName("jpatest2");
        employee.setBirthday(new Date());
        employee.setStatus((byte) 1);
        employeeService.h_save(employee);
        /* query */
		System.out.println(h_employeeDAO.findById("100001").getName());
		System.out.println(h_employeeDAO.findById("100001").getName());
	}
	
//	@Test
	public void batisTest() {
		/* insert 
		com.xie.springcase.mybatis.domain.Employee employee = new com.xie.springcase.mybatis.domain.Employee();
		employee.setId("100023");
		employee.setName("mybatis");
		employee.setBirthday(new Date());
		employee.setResume("个人简历");
		employee.setStatus((byte)1);
		employeeService.batisSave(employee); */
		/* delete
		b_employeeDAO.deleteById("100022"); */
//		System.out.println(b_employeeDAO.selectOne(employee).getName());
		/* query */
		PageRequest pageable = new PageRequest(1, 5);
		Page<com.xie.springcase.mybatis.domain.Employee> pagelist = b_employeeDAO.selectPageList(null, pageable);
		System.out.println(pagelist.getSize()); 
		
	}
	
//	@Test
	public void jpaTest() {
		/* insert
		Employee employee = new Employee();
		employee.setId("100006");
		employee.setName("jpatest2");
		employee.setBirthday(new Date());
		employee.setStatus((byte)1);
		
		Employee employee2 = employeeService.j_save(employee);
		System.out.println(employee2.getId()); */
		/*
		System.out.println(j_employeeDao.findById("100001").getName());
		System.out.println(j_employeeDao.findById("100001").getName()); */
		/* 自定义查询 */
		List<Employee> empList = j_employeeDao.getCustomListByMap(null);
		List<Employee> empList2 = j_employeeDao.getCustomListByMap(null);
		for(Employee emp : empList) {
			System.out.println(emp.getId());
		}
		/* 查询实体部分字段
		List<Object[]> objList = j_employeeDao.getFieldByStatus((byte)2);
		try {
			List<Employee> employeeList = NativeSql.castEntity(objList, Employee.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println((objList.get(0)).length); */
		
	}
}
