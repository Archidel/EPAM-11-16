package com.epam.store.bean.entity;

import java.io.Serializable;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private boolean status;
	
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
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
		
}
