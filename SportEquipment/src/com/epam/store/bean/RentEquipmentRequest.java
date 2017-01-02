package com.epam.store.bean;

import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Rent;

public class RentEquipmentRequest extends Request{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Client client;
	private Rent rentEquipment;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Rent getRentEquipment() {
		return rentEquipment;
	}

	public void setRentEquipment(Rent rentEquipment) {
		this.rentEquipment = rentEquipment;
	}
}
