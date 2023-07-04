package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	private String username;
	private String password;
	private String email;

	@Override
	public String toString() {
		return "User [id=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	public long getId() {
		return uid;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public void setId(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
