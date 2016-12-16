package com.epam.store.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.store.DAO.SQLCommand;
import com.epam.store.DAO.StoreDAO;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.ListResponse;
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

	@Override
	public Response GetEquipmentList() throws DAOException {
		ListResponse listResponse = null;
		ArrayList<Equipment> list = new ArrayList<Equipment>();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		
		try {
			con = pool.take();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_EQUIPMENT);
			
			
			//Надо бы добавить в таблицу Equipment базы данные поле status - удалено ли снарядение или нет.
			//В замен смотрим количество снаряжения в наличии если 0 то не добавлять этот элемент в список.
			while(resultSet.next()){
				if(resultSet.getInt(5) != 0){
					Equipment equipment = new Equipment();
					equipment.setId(resultSet.getInt(1));
					equipment.setCategory(resultSet.getString(2));
					equipment.setTitle(resultSet.getString(3));
					equipment.setPrice(resultSet.getInt(4));
					equipment.setQuantity(resultSet.getInt(5));
					
					list.add(equipment);
				}
			}
			
			listResponse = new ListResponse();
			listResponse.setList(list);
			listResponse.setMessage("Equipment list was added");
			
			pool.free(con);
		} catch (InterruptedException e) {
			listResponse = new ListResponse();
			listResponse.setErrorMessage("Equipment list was not added");
			listResponse.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			listResponse = new ListResponse();
			listResponse.setErrorMessage("Error database query");
			listResponse.setStatusError(true);
			throw new DAOException(e);
		}
	
		return listResponse;
	}

	@Override
	public Response GetRentList() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}