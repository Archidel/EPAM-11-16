package com.epam.store.bean.entity;

import java.io.Serializable;
import java.util.Date;

public class Rent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idClient;
	private int idRent;
	private int idEquipment;
	private Date dateFrom;
	private Date dateTo;
	
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
	
	public Date getDateFrom() {
		return dateFrom;
	}
	
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public Date getDateTo() {
		return dateTo;
	}
	
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}
