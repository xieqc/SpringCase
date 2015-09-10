package com.xie.springcase.test;

import java.util.Date;
import java.util.List;

import com.xie.springcase.hibernate.entity.*;
import com.xie.springcase.jpa.dao.*;
import com.xie.springcase.service.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.xie.springcase.hibernate.dao.IEmployeeDAO;
import com.xie.springcase.jpa.util.NativeSql;

public class DaoTest {
    public static ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    public static IEmployeeDAO h_employeeDao = (IEmployeeDAO) ac.getBean("HEmployeeDAO");
    public static com.xie.springcase.mybatis.dao.IEmployeeDAO b_employeeDao = (com.xie.springcase.mybatis.dao.IEmployeeDAO) ac.getBean("BEmployeeDAO");
    public static EmployeeDAO j_employeeDao = (EmployeeDAO) ac.getBean("JEmployeeDAO");
    public static SysDeptDAO j_sysDeptDao = (SysDeptDAO) ac.getBean("JSysDeptDAO");
    public static SysUserDAO j_sysUserDao = (SysUserDAO) ac.getBean("JSysUserDAO");
    public static SysRoleDAO j_sysRoleDao = (SysRoleDAO) ac.getBean("JSysRoleDAO");
    public static SysFunctDAO j_sysFunctDao = (SysFunctDAO) ac.getBean("JSysFunctDAO");
    public static IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
    public static ISysDeptService sysDeptService = (ISysDeptService) ac.getBean("sysDeptService");
    public static ISysUserService sysUserService = (ISysUserService) ac.getBean("sysUserService");
    public static ISysRoleService sysRoleService = (ISysRoleService) ac.getBean("sysRoleService");
    public static ISysFunctService sysFunctService = (ISysFunctService) ac.getBean("sysFunctService");

    @Test
    public void hibernateTest() {
        System.out.println(h_employeeDao.findById("100001").getName());
        System.out.println(h_employeeDao.findById("100001").getName());
    }

    @Test
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
        b_employeeDao.deleteById("100022"); */
//		System.out.println(b_employeeDao.selectOne(employee).getName());
		/* query */
        PageRequest pageable = new PageRequest(1, 5);
        Page<com.xie.springcase.mybatis.domain.Employee> pagelist = b_employeeDao.selectPageList(null, pageable);
        System.out.println(pagelist.getSize());

    }

    @Test
    public void jpaTest() {
		/* insert
		Employee employee = new Employee();
		employee.setId("100003");
		employee.setName("王五");
		employee.setBirthday(new Date());
		employee.setStatus((byte)1);
		Employee employee2 = employeeService.j_save(employee);
		System.out.println(employee2.getId()); */
		/*
		SysDept dept = new SysDept();
		SysDept superior = new SysDept();
		superior.setId(4);
		dept.setSuperior(superior);
		dept.setName("生产三部");
		dept.setStatus((byte) 1);
		sysDeptService.j_save(dept); */
		/*
		SysUser user = new SysUser();
		Employee employee = new Employee();
		employee.setId("100001");
		user.setEmployee(employee);
		SysDept dept = new SysDept();
		dept.setId(1);
		user.setSysDept(dept);
		user.setName("zhangsan");
		user.setPassword("123456");
		user.setStatus((byte)1);
		sysUserService.save(user);*/
		/*
		SysRole role = new SysRole();
		role.setName("普通用户");
		role.setStatus((byte) 1);
		sysRoleService.save(role);*/
		/*
		SysFunct funct = new SysFunct();
		funct.setName("功能五");
		funct.setUrl("");
		funct.setStatus((byte)1);
		sysFunctService.save(funct);*/

//		System.out.println(j_employeeDao.findById("100001").getName());
//        System.out.println(sysUserService.findByName("zhangsan").getSysDept().getId());
		List<SysRole> roleList = j_sysRoleDao.findBySysUserId(1);
		System.out.println(roleList.size());
		/* 自定义查询
		List<Employee> empList = j_employeeDao.getCustomListByMap(null);
		List<Employee> empList2 = j_employeeDao.getCustomListByMap(null);
		for(Employee emp : empList) {
			System.out.println(emp.getId());
		} */
		/*
		SysUser user = j_sysUserDao.findOne(1);
		System.out.println(user.getEmployee().getName());
		*/
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
