package com.epam.store.bean;

import java.util.ArrayList;

import com.epam.store.bean.entity.Equipment;

public class ShowListResponse extends Request{
	private ArrayList<Equipment> list;

	public ArrayList<Equipment> getList() {
		return list;
	}

	public void setList(ArrayList<Equipment> list) {
		this.list = list;
	}
}
