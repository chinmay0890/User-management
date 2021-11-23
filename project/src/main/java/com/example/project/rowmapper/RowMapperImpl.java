package com.example.project.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.project.entity.Employee;

public class RowMapperImpl  implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setDob(rs.getString(3));
		emp.setAge(rs.getInt(4));
		emp.setMob(rs.getLong(5));
		emp.setPresentAddress(rs.getString(6));
		emp.setPermanentAddress(rs.getString(7));
		emp.setEmail(rs.getString(8));
		emp.setPwd(rs.getString(9));
		emp.setRole(rs.getInt(10));
		emp.setStatus(rs.getInt(11));
		return emp;
	}

}
