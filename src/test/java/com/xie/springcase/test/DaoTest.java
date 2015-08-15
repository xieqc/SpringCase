package com.xie.springcase.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.xie.springcase.hibernate.dao.IEmployeeDAO;
import com.xie.springcase.hibernate.entity.Employee;
import com.xie.springcase.jpa.dao.EmployeeDAO;
import com.xie.springcase.jpa.util.NativeSql;
import com.xie.springcase.service.IEmployeeService;

public class DaoTest {
	public static ApplicationContext ac =new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
	public static IEmployeeDAO employeeDAO = (IEmployeeDAO)ac.getBean("employeeDAO");
	public static com.xie.springcase.mybatis.dao.IEmployeeDAO empDAO = (com.xie.springcase.mybatis.dao.IEmployeeDAO)ac.getBean("BEmployeeDAO");
	public static EmployeeDAO j_employeeDao = (EmployeeDAO)ac.getBean("JEmployeeDAO");
	public static IEmployeeService employeeService = (IEmployeeService)ac.getBean("employeeService");
	
//	@Test
	public void runTest() {
		System.out.println(employeeDAO.findById("100001").getName());
		System.out.println(employeeDAO.findById("100001").getName());
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
		empDAO.deleteById("100022"); */
//		System.out.println(empDAO.selectOne(employee).getName());
		/* query */
		PageRequest pageable = new PageRequest(1, 5);
		Page<com.xie.springcase.mybatis.domain.Employee> pagelist = empDAO.selectPageList(null, pageable);
		System.out.println(pagelist.getSize()); 
		
	}
	
	@Test
	public void jpaTest() {
		/*
		Employee employee = new Employee();
		employee.setId("100021");
		employee.setName("jpatest2");
		employee.setBirthday(new Date());
		employee.setStatus((byte)1);
		
		Employee employee2 = employeeService.save(employee);
		System.out.println(employee2.getId());*/
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
