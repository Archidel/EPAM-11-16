package com.epam.store.bean;

public class RegisterClientRequest extends Request{
	private String nameClient;
	private String surnameCline;
	
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getSurnameCline() {
		return surnameCline;
	}
	public void setSurnameCline(String surnameCline) {
		this.surnameCline = surnameCline;
	}
}