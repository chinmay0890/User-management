package com.example.project.entity;

public class PresentAddress {

	public PresentAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PresentAddress(int address_id, int employee_id, String present_address) {
		super();
		this.address_id = address_id;
		this.employee_id = employee_id;
		this.present_address = present_address;
	}

	

	public int getAddress_id() {
		return address_id;
	}



	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}



	public int getEmployee_id() {
		return employee_id;
	}



	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}



	public String getPresent_address() {
		return present_address;
	}



	public void setPresent_address(String present_address) {
		this.present_address = present_address;
	}



	private int address_id;
	private int employee_id;
	private String present_address;

}
