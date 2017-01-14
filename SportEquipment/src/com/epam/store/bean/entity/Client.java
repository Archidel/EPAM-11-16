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
		
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj){
			return true;
		}
		
		if (obj == null){
			return false;
		}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		Client client = (Client) obj;
		
		if (id != client.id){
			return false;
		}
		
		if (status != client.status){
			return false;
		}
		
		if (name == null) {
			if (client.name != null){
				return false;
			}
		} else{
			return name.equals(client.name);
		}
		
		if (surname == null) {
			if (client.surname != null){
				return false;
			}
		} else{
			return surname.equals(client.surname);
		}
	
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surname=" + surname + ", status=" + status + "]";
	}

}
