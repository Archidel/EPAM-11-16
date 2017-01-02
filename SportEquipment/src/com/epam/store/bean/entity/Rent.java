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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + idClient;
		result = prime * result + idEquipment;
		result = prime * result + idRent;
		result = prime * result + (status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (idClient != other.idClient)
			return false;
		if (idEquipment != other.idEquipment)
			return false;
		if (idRent != other.idRent)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rent [idClient=" + idClient + ", idRent=" + idRent + ", idEquipment=" + idEquipment + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", status=" + status + "]";
	}

	
	
}
