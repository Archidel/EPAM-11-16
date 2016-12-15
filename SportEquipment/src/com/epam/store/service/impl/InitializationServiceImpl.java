package com.epam.store.service.impl;

import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.DAO.impl.InitializationDAOImpl;
import com.epam.store.bean.Response;
import com.epam.store.service.InitializationService;
import com.epam.store.service.ServiceException;

public class InitializationServiceImpl implements InitializationService {

	@Override
	public Response initDAO() throws ServiceException {
		Response response = null;
		InitializationDAO initializationDAO = new InitializationDAOImpl();
	
		try {
			response = initializationDAO.initDAO();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return response;
	}
}
