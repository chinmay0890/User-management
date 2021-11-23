package com.example.project.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.project.entity.Employee;
import com.example.project.rowmapper.CommonLoginRowMapper;
import com.example.project.rowmapper.EmailRowMapper;
import com.example.project.rowmapper.LoginRowMapper;
import com.example.project.rowmapper.LoginidRowMapper;
import com.example.project.rowmapper.MobRowMapper;
import com.example.project.rowmapper.RowMapperImpl;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	JdbcTemplate template;
	
	//ADD AN EMPLOYEE
	@Override
	public int add(Employee emp) {
		int i = 1;
		try {
			String query = "INSERT INTO employee(name,dob,age,mob,present_address,permanent_address,email,pwd,created_time,updated_time) "
				       + "VALUES('"+emp.getName()+"','"+emp.getDob()+"',"+emp.getAge()+","+emp.getMob()+",'"+emp.getPresentAddress()+"','"+emp.getPermanentAddress()+"','"+emp.getEmail()+"','"+emp.getPwd()+"',now(),now());";
			template.execute(query);
		} 
		catch (Exception e) {
			i=0;
		}
		return i;
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM employee WHERE id="+id ;
		template.execute(query);
	}
	
	//GET ONE EMPLOYEE
	@Override
	public Employee get(int id) {
		String sql = "SELECT * FROM employee where id = "+id;
		RowMapper<Employee> rowMapper = new RowMapperImpl();
		Employee emp = template.queryForObject(sql, rowMapper);
		return emp;
	}

	//GET ALL EMPLOYEE
	@Override
	public List<Employee> getAll() {
		String query = "SELECT * FROM employee";
		RowMapper<Employee> rowMapper = new RowMapperImpl();
		List<Employee> list = template.query(query, rowMapper);
		return list;
	}

	//UPDATE PHONE
	@Override
	public void updatePhone(long oldPhone, long newPhone) {
		String query = "UPDATE employee SET mob = "+newPhone+" , updated_time=now() WHERE mob = "+oldPhone+" ;";
		//RowMapper <Employee> rowMapper = new RowMapperImpl();
		template.update(query);
	}

	//UPDATE EMAIL
	@Override
	public void updateEmail(String currEmail, String newEmail) {
		String query = "UPDATE employee SET email = '"+newEmail+"' , updated_time=now() WHERE email = '"+currEmail+"' ;";
		int i =template.update(query);
		System.out.println(i);
	}

	//UPDATE PRESENT ADDRESS
	@Override
	public void updatePresentAddress(String pwd, String newPresentAddress) {
		String query = "UPDATE employee SET present_address = '"+newPresentAddress+"' , updated_time=now() WHERE pwd = '"+pwd+"'" ;
		template.update(query);
	}
	
	
	//CHECK USER CREDENTIALS
	@Override
	public Employee login(Employee emp) {
		String query = "SELECT email,pwd FROM employee WHERE email = '"+emp.getEmail()+"' ;";
		RowMapper<Employee> rowMapper = new LoginRowMapper();
		Employee emp1 = null;
		try {
		 emp1 = template.queryForObject(query, rowMapper);
		}catch(Exception e) {
//			emp1 = new Employee();
		}
		return emp1;
	}
	
	//CHECK EMAIL
	@Override
	public Employee checkEmail(Employee emp) {
		Employee emp1 =null;
		String query = "SELECT email FROM employee WHERE email= '"+emp.getEmail()+"'";
		RowMapper<Employee> rowMapper = new EmailRowMapper();
		try {
			emp1 = template.queryForObject(query, rowMapper);
		}
		catch(Exception e){
		}
		return emp1;
	}
	
	
	//RESET PASSWORD
	@Override
	public int forgotPassword(Employee emp) {
		int i=0;
		String query = "UPDATE employee SET pwd = '"+emp.getPwd()+"' WHERE email ='"+emp.getEmail()+"' ;" ;
		try {
			i = template.update(query);
		}
		catch (Exception e) {
		}
		return i;
	}

	//UPDATE DETAILS
	@Override
	public int updateDetails(Employee emp) {
		int i=0;
		System.out.println(emp.toString());
		String query = "UPDATE employee SET name='"+emp.getName()+"', dob='"+emp.getDob()+"', age="+emp.getAge()+", mob="+emp.getMob()+", present_address='"+emp.getPresentAddress()+"', permanent_address='"+emp.getPermanentAddress()+"', email='"+emp.getEmail()+"', updated_time=now() WHERE id="+emp.getId() ;
		try {
			i = template.update(query);
			
		} 
		catch (Exception e) {
		}
		return i;
	}
	
	//GET ID,NAME USING EMAIL
	@Override
	public Employee loginid(Employee emp) {
		String query = "SELECT id, name FROM employee WHERE email='"+emp.getEmail()+"'";
		RowMapper<Employee> rowMapper = new LoginidRowMapper();
		Employee emp1 = template.queryForObject(query, rowMapper);
		return emp1;
	}

	//CHECK MOB
	@Override
	public Employee checkMob(Employee emp) {
		Employee emp1 =null;
		try {
			String query ="SELECT mob FROM employee WHERE mob='"+emp.getMob()+"'";
			RowMapper<Employee> rowMapper = new MobRowMapper();
			emp1 = template.queryForObject(query, rowMapper);
		} 
		catch (Exception e) {
		}
		return emp1;
	}

	@Override
	public Employee getOne(int id) {
		String query = "SELECT em.id , em.name, em.dob, em.age, em.mob, ad.present_address, em.permanent_address, em.email, em.pwd"
				+ "FROM employee AS em\r\n"
				+ "JOIN address  AS ad\r\n"
				+ "ON em.id = ad.employee_id\r\n"
				+ "WHERE em.id = "+id+"\r\n"
				+ "ORDER BY ad.address_id DESC\r\n"
				+ "LIMIT 1;";
		RowMapper<Employee> rowMapper = new RowMapperImpl();
		Employee emp = template.queryForObject(query, rowMapper );
		return emp;
		}

//---------------------------------------------------------------
//VERSION 2.0
//---------------------------------------------------------------
			
			
	//COMMON LOGIN
	@Override
	public Employee commonLogin(Employee emp) {
		Employee emp2 = null;
		String query = "SELECT id,name,pwd,role,status FROM employee WHERE email = '"+emp.getEmail()+"' ;";
		RowMapper <Employee> rowMapper = new CommonLoginRowMapper();
		try {
			emp2 = template.queryForObject(query, rowMapper);
		} catch (Exception e) {
		}
		return emp2;
	}

	//GET AN EMPLOYEE DETAILS
	@Override
	public Employee getDetails(Employee emp) {
		Employee emp2 = null;
		String query = "SELECT * FROM employee WHERE email ='"+emp.getEmail()+"' ;";
		try {
			RowMapper <Employee> rowMapper = new RowMapperImpl();
			emp2 = template.queryForObject(query, rowMapper);
		} 
		catch (Exception e) {
		}
		return emp2;
	}

	
	
	
	
	
	

	



}
