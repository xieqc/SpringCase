package com.xie.springcase.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xie.springcase.hibernate.entity.Employee;

@Component("JEmployeeDAO")
@RepositoryDefinition(domainClass = Employee.class, idClass = String.class)
public interface EmployeeDAO extends JpaRepository<Employee, String>,EmployeeDAOCustom {

	@Query("select a from Employee a where a.id = :id")
	public Employee findById(@Param("id")String id);
	
	@Query(value="select a.id, a.birthday, a.name, a.photo, a.resume, a.status from employee a where a.id = :id", nativeQuery = true)
	public Object getFieldById(@Param("id")String id);
	
	@Query("select a from Employee a where a.status = :status")
	public List<Object[]> getFieldByStatus(@Param("status")byte status);
}
