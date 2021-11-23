package com.example.project.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.example.project.entity.Employee;

public class CommonLoginRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setPwd(rs.getString(3));
		emp.setRole(rs.getInt(4));
		emp.setStatus(rs.getInt(5));
		return emp;
	}

}
