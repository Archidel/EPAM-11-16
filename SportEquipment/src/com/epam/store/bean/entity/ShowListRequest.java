package com.epam.store.bean.entity;

import java.util.ArrayList;

import com.epam.store.bean.Request;
import com.epam.store.bean.SportEquipment;

public class ShowListRequest extends Request{
	private ArrayList<SportEquipment> list;

	public ArrayList<SportEquipment> getList() {
		return list;
	}

	public void setList(ArrayList<SportEquipment> list) {
		this.list = list;
	}
}
