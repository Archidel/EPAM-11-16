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
import com.epam.store.bean.entity.Equipment;

public class StoreDAOImpl implements StoreDAO {

	@Override
	public Response AddNewClient(Client client) throws DAOException {
		Response response = null;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		
		try {
			con = pool.take();
			
			PreparedStatement preparedStatement = con.prepareStatement(SQLCommand.INSERT_CLIENT);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.executeUpdate();
	
			response = new Response();
			response.setMessage("Client was added in register");			
			
			pool.free(con);
		} catch (InterruptedException e) {
			response = new Response();
			response.setErrorMessage("Trying to add a client failed");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response = new Response();
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			throw new DAOException(e);
		}
		return response;
	}

	@Override
	public Response AddNewEquipment(Equipment equipment) throws DAOException {
		Response response = null;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		
		try {
			con = pool.take();
		
			PreparedStatement preparedStatement = con.prepareStatement(SQLCommand.INSERT_EQUIPMENT);
			preparedStatement.setString(1, equipment.getCategory());
			preparedStatement.setString(2, equipment.getTitle());
			preparedStatement.setInt(3, equipment.getPrice());
			preparedStatement.setInt(4, equipment.getQuantity());
			preparedStatement.executeUpdate();
			
			response = new Response();
			response.setMessage("Equipment was added in store");
			
			pool.free(con);
		} catch (InterruptedException e) {
			response = new Response();
			response.setMessage("Trying to add a equipment failed");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response = new Response();
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			throw new DAOException(e);
		}
		return response;
	}

}