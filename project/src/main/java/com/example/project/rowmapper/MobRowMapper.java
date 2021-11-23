package com.example.project.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.project.entity.Employee;

public class MobRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setMob(rs.getLong(1));
		return emp;
	}
	
}
