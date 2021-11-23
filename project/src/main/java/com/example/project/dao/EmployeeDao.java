package com.example.project.dao;

import java.util.List;

import com.example.project.entity.Employee;

public interface EmployeeDao {
	
	public Employee get(int id);
	public List<Employee> getAll();
	public void updatePhone(long oldPhone, long newPhone);
	public void updateEmail(String currEmail, String newEmail);
	public void updatePresentAddress(String pwd, String newPresentAddress);
	public int add(Employee emp);
	public void delete(int id);
	public Employee login(Employee emp);
	public int forgotPassword(Employee emp);
	public Employee checkEmail(Employee emp);
	public int updateDetails(Employee emp);
	public Employee loginid(Employee emp);
	public Employee checkMob(Employee emp);
	public Employee getOne(int id);
	
	//VERSION 2.0
	public Employee commonLogin(Employee emp);
	public Employee getDetails(Employee emp);
}
