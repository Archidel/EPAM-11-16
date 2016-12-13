package com.epam.shop.bean.entity;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.SportEquipment;

public class ItemRequest extends Request{
	private SportEquipment equipment;

	public SportEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(SportEquipment equipment) {
		this.equipment = equipment;
	}
}
