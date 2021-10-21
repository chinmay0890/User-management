package com.example.project.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.project.entity.Employee;
import com.example.project.entity.PresentAddress;
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
			String query = "INSERT INTO employee(name,dob,age,mob,present_address,permanent_address,email,pwd,created_by,created_time) "
				       + "VALUES('"+emp.getName()+"','"+emp.getDob()+"',"+emp.getAge()+","+emp.getMob()+",'"+emp.getPresentAddress()+"','"+emp.getPermanentAddress()+"','"+emp.getEmail()+"','"+emp.getPwd()+"',id,now());";
			template.execute(query);
		} 
		catch (Exception e) {
			i=0;
			System.out.println("error with the sql");
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
		String query = "SELECT e.id, e.name, e.dob, e.age, e.mob, a.present_address, e.permanent_address, e.email,e.pwd, e.role, e.status, e.created_by, e.updated_by, e.created_time, e.updated_time, MAX(a.address_id)\r\n"
				+ "FROM employee AS e\r\n"
				+ "LEFT JOIN address AS a \r\n"
				+ "ON e.id = a.employee_id\r\n"
				+ "GROUP BY e.id "
				+ "ORDER BY e.status, e.id;";
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

	//GET AN EMPLOYEE DETAILS BY EMAIL
	@Override
	public Employee getEmailDetails(Employee emp) {
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
	
	
	//REGISTER 
	@Override
	public int register(Employee emp) {
		int i = 0;
		String query = "INSERT INTO employee (name,dob,age,mob,permanent_address,email,pwd,role,status,created_by,updated_by,created_time) "
					+"VALUES('"+emp.getName()+"','"+emp.getDob()+"',"+emp.getAge()+","+emp.getMob()+",'"+emp.getPermanentAddress()+"','"+emp.getEmail()+"','"+emp.getPwd()+"',"+emp.getRole()+","+emp.getStatus()+",'"+emp.getCreated_by()+"','"+emp.getUpdated_by()+"',now())";
		try {
			template.execute(query);
			i = 1;
		} catch (Exception e) {
		}
		return i;
	}

	//GET AN EMPLOYEE DETAILS BY ID
	@Override
	public Employee getIdDetails(Employee emp) {
		Employee emp2 = null;
		String query = "SELECT * FROM employee WHERE id= "+emp.getId() ;
		RowMapper <Employee> rowMapper = new RowMapperImpl(); 
		try {
			emp2 = template.queryForObject(query, rowMapper);
		} 
		catch (Exception e) {
		}
		return emp2;
	}
	
	
	
	
	
	//UPDATE
	@Override
	public int update(Employee emp) {
		int i=0;
		String query = "UPDATE employee SET name='"+emp.getName()+"', dob='"+emp.getDob()+"', age="+emp.getAge()+" , mob="+emp.getMob()+" , permanent_address='"+emp.getPermanentAddress()+"', email='"+emp.getEmail()+"', role="+emp.getRole()+", status="+emp.getStatus()+", updated_by='"+emp.getUpdated_by()+"', updated_time=now() WHERE id="+emp.getId()+";";
		try {
			i = template.update(query);
		} 
		catch (Exception e) {
		}
		return i;
	}

	//INSERT PRESENT ADDRESS
	@Override
	public int insertAddress(PresentAddress address) {
		int i =0;
		String query = " INSERT INTO address (employee_id,present_address) VALUES("+address.getEmployee_id()+",'"+address.getPresent_address()+"');";
		try {
			template.execute(query);
			i=1;
		} 
		catch (Exception e) {
		}
		return i;
	}

	
	//GET AN EMPLOYEE DETAILS BY ID - 2.0
	
	@Override
	public Employee getIDDetails(Employee emp) {
		Employee emp2 = null;
		String query = "SELECT e.id, e.name, e.dob, e.age, e.mob, a.present_address, e.permanent_address, e.email, e.pwd, e.role, e.status, e.created_by, e.updated_by, e.created_time, e.updated_time,a.address_id\r\n"
				+ "FROM employee AS e \r\n"
				+ "JOIN address AS a\r\n"
				+ "ON e.id = a.employee_id \r\n"
				+ "WHERE e.id = "+emp.getId()+"\r\n"
				+ "ORDER BY a.address_id DESC\r\n"
				+ "LIMIT 1;";
		RowMapper <Employee> rowMapper = new RowMapperImpl(); 
		try {
			emp2 = template.queryForObject(query, rowMapper);
		} 
		catch (Exception e) {
		}
		return emp2;
	}
	
	
	//GET AN EMPLOYEE DETAILS BY EMAIL - 2.0
	
		@Override
		public Employee getemailDetails(Employee emp) {
			Employee emp2 = null;
			String query = "SELECT e.id, e.name, e.dob, e.age, e.mob, a.present_address, e.permanent_address, e.email, e.pwd, e.role, e.status, e.created_by, e.updated_by, e.created_time, e.updated_time,a.address_id\r\n"
					+ "FROM employee AS e \r\n"
					+ "JOIN address AS a\r\n"
					+ "ON e.id = a.employee_id \r\n"
					+ "WHERE e.email = '"+emp.getEmail()+"'\r\n"
					+ "ORDER BY a.address_id DESC\r\n"
					+ "LIMIT 1;";
			RowMapper <Employee> rowMapper = new RowMapperImpl(); 
			try {
				emp2 = template.queryForObject(query, rowMapper);
			} 
			catch (Exception e) {
			}
			return emp2;
		}

		@Override
		public int insertloop() {
			int i=1;
			for(int j=1; j<=72; j++) {
				String query = "INSERT INTO address (employee_id,present_address)\r\n"
						+ "VALUES((SELECT id FROM employee WHERE id = "+j+"),(SELECT present_address FROM employee WHERE id ="+j+" ) );";
				try {
					template.execute(query);
				} 
				catch (Exception e) {
					i=0;
				}
			}
			
			return i;
		}

		//SET CREATED BY FOR USER
		@Override
		public int setCreatedBy( int id) {
			int i=0;
			String query = "UPDATE employee SET created_by ="+id+" WHERE id =  "+id;
			try {
				template.update(query);
				i=1;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return i;
		}
	
		//INSERT LOOP
		
	
	
	
	
	
	

	



}
