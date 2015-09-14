package com.xie.springcase.hibernate.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Arrays;
import java.util.Date;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name = "employee")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String name;
	@Lob
	private byte[] photo;
	@Lob
	private String resume;
	private byte status;
	private SysUser sysUser;

	public Employee() {
	}

	public Employee(String id, String name, byte status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Id
	@Column(name = "id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "birthday")
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "photo")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Basic
	@Column(name = "resume")
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(name = "status")
	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Employee employee = (Employee) o;

		if (status != employee.status) return false;
		if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
		if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
		if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
		if (!Arrays.equals(photo, employee.photo)) return false;
		if (resume != null ? !resume.equals(employee.resume) : employee.resume != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
		result = 31 * result + (resume != null ? resume.hashCode() : 0);
		result = 31 * result + (int) status;
		return result;
	}

	@OneToOne(mappedBy = "employee")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}