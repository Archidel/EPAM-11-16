package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Equipment;

public interface StoreDAO {
	Response addNewClient(String name, String surname) throws DAOException;
	boolean existClientInDatabase(String name, String surname) throws DAOException;
	int getAmountRentedEquipment(int idClient) throws DAOException;
	int getAmountRentedEquipment(int idClient, int idEquipment) throws DAOException;
	int getIdClient(String name, String surname) throws DAOException;
	int getIdEquipment(String titleEquipment) throws DAOException;
	int getPriceEquipment(String titleEquipment) throws DAOException;
	int getQuantityEquipment(String titleEquipment) throws DAOException;
	Response addNewEquipment(Equipment equipment) throws DAOException;
	Response getEquipmentList() throws DAOException;
	Response getRentList() throws DAOException;
}
