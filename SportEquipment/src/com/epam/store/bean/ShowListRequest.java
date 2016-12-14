package com.epam.store.bean;

import java.util.ArrayList;

import com.epam.store.bean.entity.Request;
import com.epam.store.bean.entity.SportEquipment;

public class ShowListRequest extends Request{
	private ArrayList<SportEquipment> list;

	public ArrayList<SportEquipment> getList() {
		return list;
	}

	public void setList(ArrayList<SportEquipment> list) {
		this.list = list;
	}
}
