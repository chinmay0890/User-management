package com.example.project.dao;

import java.util.List;

import com.example.project.entity.Employee;
import com.example.project.entity.PresentAddress;

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
	public Employee commonLogin(Employee emp);//NO CHANGES
	public Employee getEmailDetails(Employee emp);//DONE
	public Employee getIdDetails(Employee emp);//DONE
	
	public int register(Employee emp);
	public int update(Employee emp);
	
	public int insertAddress(PresentAddress address);//NO CHANGES
	public Employee getIDDetails(Employee emp);//2.0
	public Employee getemailDetails(Employee emp);//2.0
	
	public int insertloop();
	public int setCreatedBy(int id);
}
