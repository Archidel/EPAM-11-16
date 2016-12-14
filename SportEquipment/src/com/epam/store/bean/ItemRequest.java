package com.epam.store.bean;

import com.epam.store.bean.entity.SportEquipment;

public class ItemRequest extends Request{
	private SportEquipment equipment;

	public SportEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(SportEquipment equipment) {
		this.equipment = equipment;
	}
}
