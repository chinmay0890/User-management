package com.example.project.service;



import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.project.dao.EmployeeDaoImpl;
import com.example.project.entity.Employee;
import com.example.project.entity.PresentAddress;
import com.example.project.validation.Validation;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDaoImpl dao ;
	
	
	//ADD EMPLOYEE
	public String add(Employee emp) {
		String str="";
		Employee emp1 = dao.checkEmail(emp);
		Employee emp2 = dao.checkMob(emp);
		if (emp1==null && emp2==null) {
			if(dao.add(emp)==1) {
				str = "Employee registered successfully";
			}
			else {
				str="Failed to register";
			}
		}
		else if(emp1!=null && emp2!=null) {
			str = "Email address and mobile number already exists";
		}
		else if (emp1 != null) {
			str = "Email address already exists";
		}
		else{
			str = "Mobile number already exists";
		}
		return str;
	}
	

	//GET EMPLOYEE BY ID
	public Employee get(Integer id) {
		Employee emp = dao.get(id);
		return emp;
	}
	
	
	//GET ALL EMPLOYEES
	public List<Employee> getAll(){
		return dao.getAll();
	}
	
	
	//UPDATE EMPLOYEE PHONE
	public void updatePhone(long oldPhone, long newPhone) {
		List<Employee> list = dao.getAll();
		for(Employee emp : list) {
			if(emp.getMob()==oldPhone) {
				dao.updatePhone(oldPhone, newPhone);
			}
		}	
	}
	
	//UPDATE EMPLOYEE EMAIL
	public void updateEmail(String currEmail, String newEmail) {
		List <Employee> list = dao.getAll();
		for(Employee emp : list) {
			if(emp.getEmail().equals(currEmail)) {
				dao.updateEmail(currEmail, newEmail);
			}
		}
	}
	
	//UPDATE PRESENT ADDRESS
	public void updatePresentAddress(String pwd, String newPresentAddress) {
		List<Employee> list = dao.getAll();
		for(Employee emp : list) {
			if(emp.getPwd().equals(pwd)) {
				dao.updatePresentAddress(pwd, newPresentAddress);
			}
		}
	}
	
	//CHECK USER CREDENTIALS
	public String login(Employee emp1) {
		String str="";
		Employee emp = dao.login(emp1);
		if(emp !=null) {
			if(emp1.getPwd().equals(emp.getPwd())) {
				str = "successfully logged in";
			}
			else {
				str = "incorrect password";
			}
		}
		else {
			str = "user not found";
		}	
		return str;
	}
	
	//RESET PASSWORD
	public String forgotPassword(Employee emp) {
		if(dao.forgotPassword(emp)==1) {
			return "password successfully updated";
		}
		else {
			return "Incorrect email";
		}
	}
	
	//UPDATE DETAILS
	public String updateDetails(Employee emp) {
		if(dao.updateDetails(emp)==1) {
			System.out.println("updated");
			return "Details updated successfully";
		}
		else {
			return "Failed to update details";
		}
	}
	
	//GET ID USING EMAIL
	public Employee loginid(Employee emp) {
		
		Employee emp1 = null;
		try {
		emp1 = dao.loginid(emp);
		} 
		catch (Exception e) {
		}
		return emp1;
	}
	
	//DELETE USER
	public void delete(int id) {
		dao.delete(id);
	}
	
	//---------------------------------------------------------------
	//VERSION 2.0
	//---------------------------------------------------------------
			
			
	//COMMON LOGIN
	public String commonLogin(Employee emp1) {
		String msg = "";
		if(Validation.isValidEmail(emp1.getEmail())) {
			Employee emp2 = dao.commonLogin(emp1);
			if(emp2!=null) {
				if(emp2.getStatus()==1) {
					if(emp1.getPwd().equals(emp2.getPwd())) {
						msg = "Successfully logged in";
					}
					else {
						msg = "Incorrect password";
					}
				}
				else{
					msg = "Access denied, verification is under progress";
				}
			}
			else {
				msg = "Invalid email";
			}
		}
		else {
			System.out.println("Incorrect email pattern");
		}
		return msg;
	}
	
	
	//GET AN EMPLOYEE DETAILS BY EMAIL
	public Employee getEmailDetails(Employee emp) {
		return dao.getemailDetails(emp);
	}
	
	//GET AN EMPLOYEE DETAILS BY ID
	public Employee getIdDetails(Employee emp) {
		return dao.getIDDetails(emp);
	}
	
	//UPDATE
	public int update(Employee emp1) {
		int i = 0;
		Employee emp2 = dao.getIDDetails(emp1);
		if(emp1.getPresentAddress().equals(emp2.getPresentAddress())) {
			if(dao.update(emp1)==1) {
				i = 1;
			}else {
				i = 0;
			}
		}
		else {
			if(dao.update(emp1)==1) {
				i = 1;
			}else {
				i = 0;
			}
			PresentAddress add = new PresentAddress();
			add.setEmployee_id(emp1.getId());
			add.setPresent_address(emp1.getPresentAddress());
			if(dao.insertAddress(add)==1) {
				i = 1;
			}else {
				i = 0;
			}
		}
		return i;
	}
	
	//INSERT LOOP
	public int insertloop() {
		return dao.insertloop();
	}
	
	//REGISTER
	public int register(Employee emp) {
		int i=0; 
		
		System.out.println(dao.register(emp));
		PresentAddress address = new PresentAddress();
		Employee emp1 = dao.getEmailDetails(emp);
		address.setEmployee_id(emp1.getId());
		address.setPresent_address(emp.getPresentAddress());
		System.out.println(dao.insertAddress(address));
		if(emp1.getCreated_by().equals("null")) {
			System.out.println(dao.setCreatedBy(emp1.getId()));
		}
		
		i=1;
		return i;
	}
	
	
}
