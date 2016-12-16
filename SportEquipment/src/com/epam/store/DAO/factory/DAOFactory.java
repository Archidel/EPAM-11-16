package com.epam.store.DAO.factory;

import com.epam.store.DAO.ClientDAO;
import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.impl.ClientDAOImpl;
import com.epam.store.DAO.impl.InitializationDAOImpl;
import com.epam.store.DAO.impl.StoreDAOImpl;

public class DAOFactory {
	private static DAOFactory instance = null;
	
	private InitializationDAO initializationDAO = new InitializationDAOImpl();
	private ClientDAO clientDAO = new ClientDAOImpl();
	private StoreDAO storeDAO = new StoreDAOImpl();
	
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

	public StoreDAO getStoreDAO() {
		return storeDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}
	
}