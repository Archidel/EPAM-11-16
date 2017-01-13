package com.epam.store.bean;
 
import java.util.ArrayList;

import com.epam.store.bean.entity.Equipment;

public class ListResponse extends Response{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Equipment> list;

	public ArrayList<Equipment> getList() {
		return list;
	}

	public void setList(ArrayList<Equipment> list) {
		this.list = list;
	}
	
	public Equipment getElementListByIndex(int index){
		return list.get(index);
	}	
	
}
