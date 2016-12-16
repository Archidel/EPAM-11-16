package com.epam.store.bean;

import com.epam.store.bean.entity.Equipment;

public class EquipmentRequest extends Request{
	private Equipment equipment;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
}
