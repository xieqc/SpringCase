package com.xie.springcase.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xie.javacase.protobuf.AddressBookProbuf;
import com.xie.springcase.annotation.CacheConfig;
import com.xie.springcase.hibernate.entity.Employee;
import com.xie.springcase.jpa.dao.*;
import com.xie.springcase.jpa.dao.EmployeeDAO;
import com.xie.springcase.jpa.dao.SysDeptDAO;
import com.xie.springcase.jpa.dao.SysUserDAO;
import com.xie.springcase.mybatis.dao.ISysDeptDAO;
import com.xie.springcase.mybatis.dao.impl.*;
import com.xie.springcase.mybatis.domain.SysDept;
import com.xie.springcase.script.ICalculator;
import com.xie.springcase.service.*;
import com.xie.springcase.util.JackSonParser;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.xie.springcase.hibernate.dao.IEmployeeDAO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DaoTest {
    public static ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    public static IEmployeeDAO h_employeeDao = (IEmployeeDAO) ac.getBean("HEmployeeDAO");
    public static com.xie.springcase.mybatis.dao.IEmployeeDAO b_employeeDao = (com.xie.springcase.mybatis.dao.IEmployeeDAO) ac.getBean("BEmployeeDAO");
    public static EmployeeDAO j_employeeDao = (EmployeeDAO) ac.getBean("JEmployeeDAO");
    public static SysDeptDAO j_sysDeptDao = (SysDeptDAO) ac.getBean("JSysDeptDAO");
    public static ISysDeptDAO b_sysDeptDao = (com.xie.springcase.mybatis.dao.impl.SysDeptDAO) ac.getBean("BSysDeptDAO");
	public static SysUserDAO j_sysUserDao = (SysUserDAO) ac.getBean("JSysUserDAO");
    public static SysRoleDAO j_sysRoleDao = (SysRoleDAO) ac.getBean("JSysRoleDAO");
    public static SysFunctDAO j_sysFunctDao = (SysFunctDAO) ac.getBean("JSysFunctDAO");
    public static IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
    public static ISysDeptService sysDeptService = (ISysDeptService) ac.getBean("sysDeptService");
    public static ISysUserService sysUserService = (ISysUserService) ac.getBean("sysUserService");
    public static ISysRoleService sysRoleService = (ISysRoleService) ac.getBean("sysRoleService");
    public static ISysFunctService sysFunctService = (ISysFunctService) ac.getBean("sysFunctService");
	public static ICalculator calculator = (ICalculator) ac.getBean("calculator");
	public static CacheManager ehcacheManager = (CacheManager) ac.getBean("ehcacheManager");
	private MongoTemplate mongoTemplate = (MongoTemplate) ac.getBean("mongoTemplate");
	private RedisTemplate<String, String> redisTemplate_dbmaster = (RedisTemplate<String, String>) ac.getBean("redisTemplate_db-master");

    @Test
    public void hibernateTest() {
        System.out.println(h_employeeDao.findById("100001").getName());
        System.out.println(h_employeeDao.findById("100001").getName());
    }

    @Test
    public void batisTest() {
        /* insert
        com.xie.springcase.mybatis.domain.Employee employee = new com.xie.springcase.mybatis.domain.Employee();
		employee.setId("100008");
		employee.setName("张三8");
		employee.setBirthday(new Date());
		employee.setResume("个人简历");
		employee.setStatus((byte)1);
		employeeService.b_save(employee); */

		/*
		com.xie.springcase.mybatis.domain.SysUser user = new com.xie.springcase.mybatis.domain.SysUser();
		user.setName("zhangesan");
		user.setPassword("123456");
		user.setDeptId(1);
		user.setEmployeeId("100001");
		user.setStatus(true);
		sysUserService.b_save(user); */
		/*
		SysDept dept = new SysDept();
		dept.setName("部门2");
		dept.setRemark("部门备注");
		dept.setStatus(true);
		b_sysDeptDao.insert(dept); */
        /* delete */
//        b_employeeDao.deleteById("100034");
//		System.out.println(b_employeeDao.selectOne(employee).getName());
        /* query
        PageRequest pageable = new PageRequest(1, 5);
        Page<com.xie.springcase.mybatis.domain.Employee> pagelist = b_employeeDao.selectPageList(null, pageable);
        System.out.println(pagelist.getSize()); */
		/*  */
		for(int i=0;i<3; i++) {
			com.xie.springcase.mybatis.domain.Employee employee = employeeService.b_findById("100003");
			System.out.println(employee.getName());
		}
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

//		List<Employee> employeeList = j_employeeDao.getVOByStatus((byte) 1);
//		System.out.println(j_employeeDao.findById("100001").getName());
//      System.out.println(sysUserService.findByName("zhangsan").getSysDept().getId());
		List<String> roleList = j_sysRoleDao.getRoleNameListBySysUserId(1);
		System.out.println(roleList.size());
//		System.out.println(j_sysUserDao.fetchRoleByName("zhangsan").getSysRoles().size());
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

	@Test
	public void groovyTest() {
		while(true) {
			System.out.println(calculator.add(2,3));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void ehcahceTest() {
		/* 添加数据到缓存中
		if(ehcacheManager.getCache("demoCache")==null) {
			ehcacheManager.addCache("demoCache");
		}
		Cache cache = ehcacheManager.getCache("demoCache");
		Element element = new Element("key", "val");
		cache.put(element);
		System.out.println(cache.getSize()); */
		/* */
		for(int i=0;i<3;i++) {
			System.out.println(employeeService.getCacheTest().size());
		}

	}



	@Test
	public void protobufTest() {
		AddressBookProbuf.Person.Builder personBuilder = AddressBookProbuf.Person.newBuilder();
		personBuilder.setId(1);
		personBuilder.setName("testName");
		personBuilder.setEmail("test@email.com");
		personBuilder.addPhone(AddressBookProbuf.Person.PhoneNumber.newBuilder().setNumber("131111111").setType(AddressBookProbuf.Person.PhoneType.MOBILE));
		personBuilder.addPhone(AddressBookProbuf.Person.PhoneNumber.newBuilder().setNumber("011111").setType(AddressBookProbuf.Person.PhoneType.HOME));

		AddressBookProbuf.Person person = personBuilder.build();
		byte[] personBuf = person.toByteArray();

		try {
			AddressBookProbuf.Person person2 = AddressBookProbuf.Person.parseFrom(personBuf);
			System.out.println(person2.getName() + ", " + person2.getEmail());
			List<AddressBookProbuf.Person.PhoneNumber> lstPhones = person2.getPhoneList();
			for (AddressBookProbuf.Person.PhoneNumber phoneNumber : lstPhones) {
				System.out.println(phoneNumber.getNumber());
			}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		System.out.println(personBuf);

	}

	@Test
	public void mockSimpleTest() {
		LinkedList mockedList = mock(LinkedList.class);
		// 使用mock的对象
		mockedList.add("no_1");
		mockedList.clear();
		// 验证add()和clear()行为是否发生
		verify(mockedList).add("no_1");
		verify(mockedList).clear();
		// 模拟获取第一个元素时，返回字符串first
		when(mockedList.get(0)).thenReturn("first");
		// 此时打印输出first
		System.out.println(mockedList.get(0));

		EmployeeDAO employeeDao = mock(EmployeeDAO.class);
		when(employeeDao.findById("100001").getName()).thenReturn("中");

		System.out.println(employeeDao.findById("100001").getName());
	}

	@Test
	public void mongoDBTest() {
		Employee employee = new Employee();
		employee.setId("100003");
		employee.setName("王五");
		employee.setBirthday(new Date());
		employee.setStatus((byte)1);
		/* insert */
		mongoTemplate.insert(employee);
		/* remove
		mongoTemplate.remove(employee);*/
		/* find */
		List<Employee> list = mongoTemplate.findAll(Employee.class);
		System.out.println(list.size());

	}

	@Test
	public void RedisTest() {
		Long countNum = redisTemplate_dbmaster.opsForHash().increment("server:count","countNum",1);
		System.out.println(countNum);
	}

}
