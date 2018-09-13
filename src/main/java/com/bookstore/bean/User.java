package com.bookstore.bean;



/**
 * Created by Administrator on 2018/9/11.
 */
public class User {
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String relname;
	private String sex;
	private String address;
	private String email;

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword(String password) {
		return this.password;
	}

	public String getPhone() {
		return phone;
	}

	public String getRelname() {
		return relname;
	}

	public String getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
}
