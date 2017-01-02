package com.epam.store.bean.entity;

import java.io.Serializable;

public class Rent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idClient;
	private int idRent;
	private int idEquipment;
	private String dateFrom;
	private String dateTo;
	private boolean status;
	
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdRent() {
		return idRent;
	}
	
	public void setIdRent(int idRent) {
		this.idRent = idRent;
	}
	
	public int getIdEquipment() {
		return idEquipment;
	}
	
	public void setIdEquipment(int idEquipment) {
		this.idEquipment = idEquipment;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
