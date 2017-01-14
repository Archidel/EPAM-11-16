package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;

public interface StoreDAO {
	Response addNewClient(Client client) throws DAOException;
	Client getClient(String name, String surname) throws DAOException;
	Response addNewEquipment(Equipment equipment) throws DAOException;
	Response getEquipmentList() throws DAOException;
	Response getRentList() throws DAOException;
}
