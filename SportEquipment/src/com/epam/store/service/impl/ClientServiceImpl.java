package com.epam.store.service.impl;

import com.epam.store.DAO.ClientDAO;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.DAO.factory.DAOFactory;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Rent;
import com.epam.store.service.ClientService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.valiadation.ValidationData;

public class ClientServiceImpl implements ClientService {

	@Override
	public Response RentEquipment(RentEquipmentRequest rentEquipmentRequest) throws ServiceException {
		Response response = new Response();
		
		String titleEquipment = rentEquipmentRequest.getTitle();
		Client client = rentEquipmentRequest.getClient();
		Rent rentEquipment = rentEquipmentRequest.getRentEquipment();
		
		boolean titleEquipmentIsValid = ValidationData.currentData(titleEquipment); 
		boolean clientNameIsValid = ValidationData.currentData(client.getName());
		boolean clientSurnameIsValid = ValidationData.currentData(client.getSurname());
		boolean rentDateFrom = ValidationData.currentData(rentEquipment.getDateFrom());
		boolean rentDateTo = ValidationData.currentData(rentEquipment.getDateFrom());
		
		if(titleEquipmentIsValid && clientNameIsValid && clientSurnameIsValid && rentDateFrom && rentDateTo){
			DAOFactory daoFactory = DAOFactory.getInstance();
			ClientDAO clientDAO = daoFactory.getClientDAO();
			
			try {
				response = clientDAO.RentEquipment(rentEquipmentRequest);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}	
			
		}else{
			response.setErrorMessage("Invalid title equipment or client data");
			response.setStatusError(true);
			throw new ServiceException("Invalid title equipment or client data");
		}
	
		return response;
	}

}
