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
	public Response addNewClient(String name, String surname) throws ServiceException {
		Response response = null;
		Client client = null;
		
		boolean nameIsValid = ValidationData.currentData(name);
		boolean surnameIsValid = ValidationData.currentData(surname);
		
		if(nameIsValid && surnameIsValid){

			/* Добавить алгорит по проверке client в базе данных и исходя из этого принимать решения регестрировать его
			 * или "авторизировать" 
			 */
			
			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			try {
				client = storeDAO.getClient(name, surname);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			
			//Если client == null значит пользователя в базе данных нет, если client != null пользователь в базе данных ЕСТЬ!
			if(client == null){
				
			}else{
				
			}
			
			try {
				response = storeDAO.addNewClient(client);
			} catch (DAOException e) {
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
