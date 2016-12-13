package com.epam.shop.bean.entity;

import java.util.ArrayList;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.SportEquipment;

public class ShowListRequest extends Request{
	private ArrayList<SportEquipment> list;

	public ArrayList<SportEquipment> getList() {
		return list;
	}

	public void setList(ArrayList<SportEquipment> list) {
		this.list = list;
	}
}
