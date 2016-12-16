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
	public Response AddNewClient(Client client) throws ServiceException {
		Response response = null;
		
		boolean nameIsValid = ValidationData.currentData(client.getName());
		boolean surnameIsValid = ValidationData.currentData(client.getSurname());
		
		if(nameIsValid && surnameIsValid){
			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			try {
				response = storeDAO.AddNewClient(client);
			} catch (DAOException e) {
				response = new Response();
				response.setMessage("Client was not added");
				response.setStatusError(true);
				throw new ServiceException(e);
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
	public Response AddNewEquipment(Equipment equipment) throws ServiceException {
		Response response = null;
		
		String title = equipment.getTitle();
		String category = equipment.getCategory();
		int price = equipment.getPrice();
		int quantity = equipment.getQuantity();
		
		boolean titleIsValid = ValidationData.currentData(title);
		boolean categoryIsValid  = ValidationData.currentData(category);
		
		if((price != 0) && (quantity != 0) && (titleIsValid) && (categoryIsValid)){
			
			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			try {
				response = storeDAO.AddNewEquipment(equipment);
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
	public Response GetEquipmentList() throws ServiceException {
		Response response = null;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		StoreDAO storeDAO = daoFactory.getStoreDAO();
		
		try {
			response = storeDAO.GetEquipmentList();
		} catch (DAOException e) {
			response = new Response();
			response.setErrorMessage("Equipment list can not be returned");
			response.setStatusError(true);
			throw new ServiceException(e);
		}
		
		return response;
	}

	@Override
	public Response GetRentList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
