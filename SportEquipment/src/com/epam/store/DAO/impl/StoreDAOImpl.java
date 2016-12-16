package com.epam.store.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.store.DAO.SQLCommand;
import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;

public class StoreDAOImpl implements StoreDAO {

	@Override
	public Response AddNewClient(Client client) throws DAOException {
		Response response = new Response();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		
		try {
			con = pool.take();
			
			PreparedStatement preparedStatement = con.prepareStatement(SQLCommand.INSERT_CLIENT);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.executeUpdate();
	
			response.setMessage("Client was added in register");			
			
			pool.free(con);
		} catch (InterruptedException e) {
			response.setErrorMessage("Trying to add a user failed");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			throw new DAOException(e);
		}
		return response;
	}

}