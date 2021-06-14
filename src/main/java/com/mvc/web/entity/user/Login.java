package com.mvc.web.entity.user;

public class Login {
	private String id;
	private String password;
	
	
	
	@Override
	public String toString() {
		return "Login [id=" + id + ", password=" + password + "]";
	}
	public Login(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
