package com.epam.store.bean.entity;

import com.epam.store.bean.Request;
import com.epam.store.bean.SportEquipment;

public class ItemRequest extends Request{
	private SportEquipment equipment;

	public SportEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(SportEquipment equipment) {
		this.equipment = equipment;
	}
}
