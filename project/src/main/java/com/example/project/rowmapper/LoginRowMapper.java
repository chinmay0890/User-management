package com.example.project.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.project.entity.Employee;

public class LoginRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setEmail(rs.getString(1));
		emp.setPwd(rs.getString(2));
		
		return emp;
	}
	
	

}
