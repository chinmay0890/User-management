package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.Employee;
import com.example.project.service.EmployeeService;

@CrossOrigin 
@RestController
public class Controller {
	
	@Autowired
	private EmployeeService service;
	
	
	//ADD AN EMPLOYEE
	@PostMapping("/employee/insert")
	public String add(@RequestBody Employee emp) {
		return service.add(emp);
	}

	//GET A EMPLOYEE
	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable int id) {
		Employee emp = service.get(id);
		return emp;
	}
	
	//GET ALL EMPLOYEES
	@GetMapping("/employees")
	public List<Employee> getAll(){
		List <Employee> list = service.getAll();
		return list;
	}
	
	//UPDATE EMPLOYEE
	@PostMapping("/employee/update")
	public String updateDetails(@RequestBody Employee emp) {
		return service.updateDetails(emp);
	}
	
	@PutMapping("/employee/updatePhone/{oldPhone}/{newPhone}")
	public void update(@PathVariable long oldPhone, @PathVariable long newPhone ) {
		service.updatePhone(oldPhone, newPhone);
	}
	
	@PutMapping("/employee/updateEmail/{currEmail}/{newEmail}")
	public void updateEmail(@PathVariable String currEmail, @PathVariable String newEmail ) {
		service.updateEmail(currEmail, newEmail);
	}
	
	@PutMapping("/employee/update/presentAddress/{pwd}/{newPresentAddress}")
	public void updateAddress(@PathVariable String pwd, @PathVariable String newPresentAddress ) {
		service.updatePresentAddress(pwd, newPresentAddress);
	}
	
	//CHECK CREDENTIALS OF USER
	@PostMapping("/employee/login")
	public String login(@RequestBody Employee emp) {
		return service.login(emp);
	}
	
	//FORGOT PASSWORD
	@PostMapping("/employee/forgotPassword")
	public String forgotPassword(@RequestBody Employee emp) {
		return service.forgotPassword(emp);
	}
	
	//DELETE USER
	@DeleteMapping("/employee/delete/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
	
	//GET ID USING EMAIL
	@PostMapping("/employee/loginid")
	public Employee loginid(@RequestBody Employee emp) {
		return service.loginid(emp);
	}
	
	
	//---------------------------------------------------------------
	//VERSION 2.0
	//---------------------------------------------------------------
		
	
	//COMMON LOGIN
	@PostMapping("/employee/commonlogin")
	public String commonLogin(@RequestBody Employee emp) {
		return service.commonLogin(emp);
	}
	
	//GET AN EMPLOYEE DETAILS
	@PostMapping("/employee/getDetails")
	public Employee getDetails(@RequestBody Employee emp) {
		return service.getDetails(emp);
	}
	
	//SIGNUP
	@PostMapping("/employee/signup")
	public String signup(@RequestBody Employee emp) {
		
	}
	
}
