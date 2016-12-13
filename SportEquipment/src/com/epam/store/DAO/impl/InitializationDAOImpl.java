package com.epam.store.DAO.impl;

import java.sql.SQLException;

import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.bean.Response;

public class InitializationDAOImpl implements InitializationDAO {

	@Override
	public Response initDAO() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Response response = new Response();
		
		try {
			connectionPool.init();
			response.setMessage("database has been initialized");
		} catch (SQLException e) {
			response.setErrorMessage("database has been initialized");
			response.setStatusError(true);
			e.printStackTrace();
		}
		return response;
	}
	
}
