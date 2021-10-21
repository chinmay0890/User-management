package com.example.project.entity;


public class Employee {

	//FIELDS
	private int id;
	private String name;
	private String dob;
	private int age;
	private long mob;
	private String presentAddress;
	private String permanentAddress;
	private String email;
	private String pwd;
	private int role;
	private int status;
	private String created_by;
	private String updated_by;
	
	

	

	//DEFAULT CONSTRUCTOR 
	public Employee() {
		super();
	}
	
	//CONSTRUCTORS WITH FIELDS
	public Employee(int id, String name, String dob, int age, long mob, String presentAddress, String permanentAddress,
			String email, String pwd, int role, int status, String created_by, String updated_by) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.mob = mob;
		this.presentAddress = presentAddress;
		this.permanentAddress = permanentAddress;
		this.email = email;
		this.pwd = pwd;
		this.role = role;
		this.status = status;
		this.created_by = created_by;
		this.updated_by = updated_by;
	}
	
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	
	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}
	
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", age=" + age + ", mob=" + mob
				+ ", presentAddress=" + presentAddress + ", permanentAddress=" + permanentAddress + ", email=" + email
				+ ", pwd=" + pwd + ", role=" + role + ", status=" + status + ", created_by=" + created_by
				+ ", updated_by=" + updated_by + "]";
	}


}