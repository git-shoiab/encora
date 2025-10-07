package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	private Long userid;
	private String username;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Long userid,String username) {
		this.userid=userid;
		this.username=username;
	}
	
	@Override
	public String toString() {
		return userid+":"+username;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
