package com.epam.store.DAO.impl;

import java.sql.SQLException;
import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.bean.Response;
import com.epam.store.controller.logging.StoreLogger;

public class InitializationDAOImpl implements InitializationDAO {

	@Override
	public Response initDAO() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Response response = new Response();
		
		try {
			connectionPool.init();
			response.setMessage("Database has been initialized");
		} catch (SQLException e) {
			response.setErrorMessage("Database has not been initialized");
			response.setStatusError(true);
			StoreLogger.getLog().error(e);
		}
		
		return response;
	}
	
}
