package com.epam.store.bean;

import java.util.ArrayList;

import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;

public class RentListResponse extends Response{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Client> clientList;
	private ArrayList<Equipment> equipmentList;
	private ArrayList<Rent> rentList;
	
	public ArrayList<Rent> getRentList() {
		return rentList;
	}
	
	public void setRentList(ArrayList<Rent> rentList) {
		this.rentList = rentList;
	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}
	
	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}
	
	public ArrayList<Equipment> getEquipmentList() {
		return equipmentList;
	}
	
	public void setEquipmentList(ArrayList<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	
	
	
	
}
