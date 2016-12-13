package com.epam.store.service.impl;

import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.impl.InitializationDAOImpl;
import com.epam.store.bean.Response;
import com.epam.store.service.InitializationService;

public class InitializationServiceImpl implements InitializationService {

	@Override
	public Response initDAO() {
		Response response = null;
		InitializationDAO initializationDAO = new InitializationDAOImpl();
		response = initializationDAO.initDAO();
		return response;
	}

	@Override
	public Response initStore() {
		Response response = null;
		
		
		
		return null;
	}

}
