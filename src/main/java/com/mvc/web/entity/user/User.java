package com.mvc.web.entity.user;

import java.util.Date;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private Date regdate; // 가입일자
	private String rank; // 사용자 등급
	private int number; // 조회수 리턴될 결과값;
	private String flag;
	
	public User() {

	}
	
	public User(String rank) {
		this.rank = rank;
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	//user  생성용
	public User(String id, String password, String name, String email) {

		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	// user 출력용
	public User(String id, String password, String name, String email, Date regdate, String rank, int number) {

		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.regdate = regdate;
		this.rank = rank;
		this.number = number;
	}

	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", regdate="
				+ regdate + ", rank=" + rank + ", number=" + number + ", flag=" + flag + "]";
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}