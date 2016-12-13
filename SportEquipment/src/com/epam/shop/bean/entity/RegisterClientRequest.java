package com.epam.shop.bean.entity;

import com.epam.shop.bean.Request;

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
