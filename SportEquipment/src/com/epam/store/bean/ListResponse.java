package com.epam.store.bean;

import java.util.ArrayList;

import com.epam.store.bean.entity.Equipment;

public class ListResponse extends Response{
	private ArrayList<Equipment> list;
	private String type; //Лучше добавить enum (наверное)

	public ArrayList<Equipment> getList() {
		return list;
	}

	public void setList(ArrayList<Equipment> list) {
		this.list = list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Equipment getElementListByIndex(int index){
		return list.get(index);
	}
	
	
}
