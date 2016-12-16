package com.epam.store.service.impl;

import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.DAO.factory.DAOFactory;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.valiadation.ValidationData;

public class StoreServiceImpl implements StoreService {

	@Override
	public Response AddNewClient(Client client) throws ServiceException {
		Response response = null;
		
		if(ValidationData.currentData(client.getName()) && (ValidationData.currentData(client.getSurname()))){
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

}
