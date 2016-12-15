package com.epam.store.bean;

public class RegisterClientRequest extends Request{
	private String nameClient;
	private String surnameClient;
	
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getSurnameClient() {
		return surnameClient;
	}
	public void setSurnameClient(String surnameClient) {
		this.surnameClient = surnameClient;
	}
	
}
