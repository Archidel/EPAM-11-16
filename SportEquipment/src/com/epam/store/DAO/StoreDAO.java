package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;

public interface StoreDAO {
	Response AddNewClient(Client client) throws DAOException;
	Response AddNewEquipment(Equipment equipment) throws DAOException;
	Response GetEquipmentList() throws DAOException;
	Response GetRentList() throws DAOException;
}
