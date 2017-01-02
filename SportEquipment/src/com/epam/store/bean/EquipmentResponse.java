package com.epam.store.bean;

import com.epam.store.bean.entity.Equipment;

public class EquipmentResponse extends Response{
	private Equipment equipment;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	
}
