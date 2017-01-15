package com.epam.store.service.impl;

import java.util.ArrayList;

import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.DAO.factory.DAOFactory;
import com.epam.store.bean.RentListResponse;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.valiadation.ValidationData;

public class StoreServiceImpl implements StoreService {

	@Override
	public Response addNewClient(String name, String surname) throws ServiceException {
		Response response = null;
		
		boolean nameIsValid = ValidationData.currentData(name);
		boolean surnameIsValid = ValidationData.currentData(surname);
		
		if(nameIsValid && surnameIsValid){

			DAOFactory factory = DAOFactory.getInstance();
			StoreDAO storeDAO = factory.getStoreDAO();
			
			boolean existClientInDatabase = false;
				
			try {
				existClientInDatabase = storeDAO.existClientInDatabase(name, surname); //Определяем зарегистрирован ли пользователь в базе данных или нет
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			
			if(existClientInDatabase){
				response = new Response();
				response.setMessage("Client " + surname + " " + name + " is already registered in the database and doesn't require new registration");
			}else{
				try {
					response = storeDAO.addNewClient(name, surname);
				} catch (DAOException e) {
					throw new ServiceException("Client was not added", e);
				}	
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
			throw new ServiceException(e);
		}
		
		return response;
	}

	@Override
	public Response getRentList() throws ServiceException {
		RentListResponse rentListResponse = null;
		
		ArrayList<Rent> rentList = null;
		ArrayList<Client> clientList = null;
		ArrayList<Equipment> equipmentList = null;
		rentListResponse = new RentListResponse();
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		StoreDAO storeDAO = daoFactory.getStoreDAO();

		try {
			rentList = storeDAO.getRentList();
			clientList = storeDAO.getClientRentList(rentList);
			equipmentList = storeDAO.getEquipmentRentList(rentList);
 		} catch (DAOException e) {
 			throw new ServiceException(e);
		}
		
		rentListResponse.setRentList(rentList);
		rentListResponse.setClientList(clientList);
		rentListResponse.setEquipmentList(equipmentList);
		rentListResponse.setMessage("Rent list was added");
	
		return rentListResponse;
	}

}
