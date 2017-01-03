package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;

public interface ClientDAO {
	Response RentEquipment(RentEquipmentRequest rentEquipmentRequest) throws DAOException;
	Response ReturnEquipment(RentEquipmentRequest returnEquipmentRequest) throws DAOException;
}	
