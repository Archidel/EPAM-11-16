package com.epam.store.service.impl;

import java.time.LocalDate;
import java.time.Period;

import com.epam.store.DAO.ClientDAO;
import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.connection.DBParameter;
import com.epam.store.DAO.connection.DBResourceManager;
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
	public Response rentEquipment(RentEquipmentRequest rentEquipmentRequest) throws ServiceException {
		Response response = null;
		//Количество снаряжения которое может взять клиент. 
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		int amountOfEquipment = Integer.parseInt(resourceManager.getValue(DBParameter.DB_AMOUNT_OF_EQUIPMENT));
		
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
			StoreDAO storeDAO = daoFactory.getStoreDAO();
			ClientDAO clientDAO = daoFactory.getClientDAO();
			
			boolean existClient = false;
			
			try {
				existClient = storeDAO.existClientInDatabase(client.getName(), client.getSurname()); // Зарегестрирован ли пользователь в базе данных
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			
			if(existClient){
				int idClient = 0;
				int countRentedEquipmentByClient = 0;
				int quantityEquipment = 0;
				
				try {
					idClient = storeDAO.getIdClient(client.getName(), client.getSurname()); //Определяем id клиента в базе данных
					client.setId(idClient);
					countRentedEquipmentByClient = storeDAO.getAmountRentedEquipment(idClient); //Определяем количество взятых им в аренду снаряжений
					quantityEquipment = storeDAO.getQuantityEquipment(titleEquipment); //Определяем сколько снаряжений(шт.) есть в наличии
				} catch (DAOException e) {
					throw new ServiceException(e);
				}
				
				//Проверка на количество взятых в аренду снаряжений клиентом
				if(countRentedEquipmentByClient >= amountOfEquipment){
					response = new Response();
					response.setMessage("The client has rented maximum amount of equipment");
				}else{
					if(quantityEquipment > 0){
		
						int priceEquipment = 0;
						int totalPriceEquipment = 0;
						
						try {
							priceEquipment = storeDAO.getPriceEquipment(titleEquipment); //Получаем стоймость аренды на это снаряжения
							totalPriceEquipment = calcTotalPrice(rentEquipment.getDateFrom(), rentEquipment.getDateTo(), priceEquipment);// Расчитываем полную стоймость арены на срок аренды
						} catch (DAOException e) {
							throw new ServiceException(e);
						}
					
						rentEquipment.setTotalPrice(totalPriceEquipment); // Помечаем в запрос полученый результат польной стоймости аренды
										
						try {
							response = clientDAO.rentEquipment(rentEquipmentRequest); // Арендуем
						} catch (DAOException e) {
							throw new ServiceException(e);
						}	
					
					}else{
						response = new Response();
						response.setMessage("Equipment( " + titleEquipment + ") not available");
					}
				}
			}else{
				response = new Response();
				response.setMessage("Client " + client.getSurname() + " " + client.getName() + " is not registered in the database");
			}
		}else{
			response = new Response();
			response.setErrorMessage("Invalid title equipment or client data or rent date");
			response.setStatusError(true);
			throw new ServiceException("Invalid title equipment or client data or rent date");
		}
		
		return response;
	}

	@Override
	public Response returnEquipment(RentEquipmentRequest returnEquipmentRequest) throws ServiceException {
		Response response = null;
		
		String titleEquipment = returnEquipmentRequest.getTitle();
		Client client = returnEquipmentRequest.getClient();
		
		boolean titleEquipmentIsValid = ValidationData.currentData(titleEquipment); 
		boolean clientNameIsValid = ValidationData.currentData(client.getName());
		boolean clientSurnameIsValid = ValidationData.currentData(client.getSurname());
		
		if(titleEquipmentIsValid && clientNameIsValid && clientSurnameIsValid){
			
			DAOFactory daoFactory = DAOFactory.getInstance();
			StoreDAO storeDAO = daoFactory.getStoreDAO();
			ClientDAO clientDAO = daoFactory.getClientDAO();
			
			boolean existClient = false;
			
			try {
				existClient = storeDAO.existClientInDatabase(client.getName(), client.getSurname()); // Зарегестрирован ли пользователь в базе данных
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			
			if(existClient){
				int idClient = 0;
				int idEquipment = 0;
				
				try {
					idClient = storeDAO.getIdClient(client.getName(), client.getSurname()); //Определяем id клиента в базе данных
					client.setId(idClient);
					idEquipment = storeDAO.getIdEquipment(titleEquipment);
				} catch (DAOException e) {
					throw new ServiceException(e);
				}
			
				try {
					response = clientDAO.returnEquipment(returnEquipmentRequest, idEquipment);
				} catch (DAOException e) {
					throw new ServiceException(e);
				}
				
			}else{
				response = new Response();
				response.setMessage("Client " + client.getSurname() + " " + client.getName() + " is not registered in the database");
			}
			
		}else{
			response = new Response();
			response.setErrorMessage("Invalid title equipment or client data");
			response.setStatusError(true);
			throw new ServiceException("Invalid title equipment or client data");
		}
		
		return response;
	}

	private int calcTotalPrice(String dateFrom, String dateTo, int price){
		int yearFrom = Integer.parseInt(dateFrom.substring(0, 4));
		int monthFrom = Integer.parseInt(dateFrom.substring(5, 7));
		int dayFrom = Integer.parseInt(dateFrom.substring(8, 10));
		int yearTo = Integer.parseInt(dateTo.substring(0, 4));
		int monthTo = Integer.parseInt(dateTo.substring(5, 7));
		int dayTo = Integer.parseInt(dateTo.substring(8, 10));
		
		LocalDate localDateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
		LocalDate localDateTo = LocalDate.of(yearTo, monthTo, dayTo);
		
	    Period period = Period.between(localDateFrom, localDateTo);
	    
	    int amountdays = period.getDays() + (period.getMonths() * 30);
	    
	    if(period.getYears() != 0) 
	    	amountdays = period.getYears() * 365;
	    
	    return amountdays * amountdays;
	}
	
}