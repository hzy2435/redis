package com.redis.example.ext1.domain;

import java.util.Date;

public class Employee {

	private Long empId;
	private String empName;
	private Date birthday;
	private float salary;
	private String department;
	
	public Employee() {
		
	}
	public Employee(Long empId, String empName, Date birthday, float salary, String department) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.birthday = birthday;
		this.salary = salary;
		this.department = department;
	}


	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", birthday=" + birthday + ", salary=" + salary
				+ ", department=" + department + "]";
	}
}
