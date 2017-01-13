package com.epam.store.service.impl;

import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.DAO.factory.DAOFactory;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.valiadation.ValidationData;

public class StoreServiceImpl implements StoreService {

	@Override
	public Response addNewClient(Client client) throws ServiceException {
		Response response = null;
		
		boolean nameIsValid = ValidationData.currentData(client.getName());
		boolean surnameIsValid = ValidationData.currentData(client.getSurname());
		
		if(nameIsValid && surnameIsValid){
			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			try {
				response = storeDAO.addNewClient(client);
			} catch (DAOException e) {
				response = new Response();
				response.setMessage("Client was not added");
				response.setStatusError(true);
				throw new ServiceException("Client was not added", e);
			}
			
		}else{
			response = new Response();
			response.setErrorMessage("Invalid user data");
			response.setStatusError(true);
			throw new ServiceException("Invalid user data");
		}
		
		return response;
	}

	@Override
	public Response addNewEquipment(Equipment equipment) throws ServiceException {
		Response response = null;
		
		boolean priceIsValid = ValidationData.numberOfVarification(equipment.getPrice());
		boolean quantityIsValid = ValidationData.numberOfVarification(equipment.getQuantity());
		boolean titleIsValid = ValidationData.currentData(equipment.getTitle());
		boolean categoryIsValid  = ValidationData.currentData(equipment.getCategory());
		
		if(priceIsValid && quantityIsValid && titleIsValid && categoryIsValid){
			
			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			try {
				response = storeDAO.addNewEquipment(equipment);
			} catch (DAOException e) {
				response = new Response();
				response.setErrorMessage("Equipment was not added");
				response.setStatusError(true);
				throw new ServiceException(e);
			}
			
		}else{
			response = new Response();
			response.setErrorMessage("Invalid equipment data");
			response.setStatusError(true);
		}
		
		return response;
	}

	@Override
	public Response getEquipmentList() throws ServiceException {
		Response response = null;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		StoreDAO storeDAO = daoFactory.getStoreDAO();
		
		try {
			response = storeDAO.getEquipmentList();
		} catch (DAOException e) {
			response = new Response();
			response.setErrorMessage("Equipment list can not be returned");
			response.setStatusError(true);
			throw new ServiceException("Equipment list can not be returned", e);
		}
		
		return response;
	}

	@Override
	public Response getRentList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
