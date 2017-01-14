package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;

public interface ClientDAO {
	Response rentEquipment(RentEquipmentRequest rentEquipmentRequest) throws DAOException;
	Response returnEquipment(RentEquipmentRequest returnEquipmentRequest, int idEquipment) throws DAOException;
}	
