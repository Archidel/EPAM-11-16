package com.epam.store.DAO;

import com.epam.store.DAO.impl.InitializationDAOImpl;

public class DAOFactory {
	private static DAOFactory instance = null;
	
	private InitializationDAO initializationDAO = new InitializationDAOImpl();
	
	private DAOFactory() {}

	public static DAOFactory getInstance() {
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}

	public InitializationDAO getInitializationDAO() {
		return initializationDAO;
	}
	
}