package com.epam.store.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.store.DAO.ClientDAO;
import com.epam.store.DAO.SQLCommand;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.EquipmentResponse;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;
import com.epam.store.controller.logging.StoreLogger;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public Response rentEquipment(RentEquipmentRequest rentEquipmentRequest) throws DAOException {
		EquipmentResponse equipmentResponse = new EquipmentResponse();
		
		String titleEquipment = rentEquipmentRequest.getTitle();
		Client client = rentEquipmentRequest.getClient();
		Rent rentEquipment = rentEquipmentRequest.getRentEquipment();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = pool.take();
			statement = con.createStatement();
				
			resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_EQUIPMENT);
			preparedStatement = con.prepareStatement(SQLCommand.INSERT_RENT_EQUIPMENT);
			Equipment equipment = null;
			
			while(resultSet.next()){
				if(resultSet.getString(3).equalsIgnoreCase(titleEquipment)){
					equipment = new Equipment();
					equipment.setId(resultSet.getInt(1));
					equipment.setTitle(resultSet.getString(3));
					equipment.setCategory(resultSet.getString(2));
					equipment.setPrice(resultSet.getInt(4));
					equipment.setQuantity(resultSet.getInt(5));
					
					equipmentResponse.setEquipment(equipment);
					equipmentResponse.setMessage("Equipment "+ equipment.getTitle() +" was rented by " + client.getName() + " " + client.getSurname());
					break;
				}
			}
			
			preparedStatement.setInt(1, client.getId());
			preparedStatement.setInt(2, equipment.getId());
			preparedStatement.setString(3, rentEquipment.getDateFrom());
			preparedStatement.setString(4, rentEquipment.getDateTo());
			preparedStatement.setInt(5, rentEquipment.getTotalPrice()); //Значение по умолчанию!!! НЕ СДЕЛАН расчёт общей цены на арену (сделать на слое service)
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = con.prepareStatement(SQLCommand.UPDATE_QUANTITY_DECREMENT_FROM_EQUIPMENT);
			preparedStatement.setInt(1, equipment.getId());
			preparedStatement.executeUpdate();
			
		} catch (InterruptedException e) {
			equipmentResponse.setErrorMessage("Attempt to give a client the equipment fails");
			equipmentResponse.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			equipmentResponse.setErrorMessage("Error database query");
			equipmentResponse.setStatusError(true);
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, preparedStatement, resultSet);
		}
			
		return equipmentResponse;
	}

	@Override
	public Response returnEquipment(RentEquipmentRequest returnEquipmentRequest, int idEquipment) throws DAOException {
		Response response = new Response();
		
		String titleEquipment = returnEquipmentRequest.getTitle();
		Client client = returnEquipmentRequest.getClient();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			preparedStatement = con.prepareStatement(SQLCommand.UPDATE_CHANGE_STATUS_FROM_RENT);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, client.getId());
			preparedStatement.setInt(3, idEquipment);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = con.prepareStatement(SQLCommand.UPDATE_QUANTITY_INCREMENT_FROM_EQUIPMENT);
			preparedStatement.setInt(1, idEquipment);
			preparedStatement.executeUpdate();

			response.setMessage("Equipment " + titleEquipment + " was returned by " + client.getName() + " " + client.getSurname());

		} catch (InterruptedException e) {
			response.setErrorMessage("Attempt to return a client the equipment fails");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, preparedStatement, resultSet);
		}
			return response;
		}

	private void close(ConnectionPool pool, Connection con, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet){
		try { 
			if (resultSet != null) 
				resultSet.close(); 
			} catch (SQLException e) {
				StoreLogger.getLog().error("ResultSet isn't closed", e);
			}
		
		try { 
			if (statement != null) 
				statement.close(); 
			} catch (SQLException e) {
				StoreLogger.getLog().error("Statement isn't closed", e);
			}
		
		try { 
			if (preparedStatement != null) 
				preparedStatement.close(); 
			} catch (SQLException e) {
				StoreLogger.getLog().error("PreparedStatement isn't closed", e);
			}
		
		try { 
			if (pool != null) 
				pool.free(con); 
			} catch (InterruptedException | DAOException e) {
				StoreLogger.getLog().error("Connection isn't return to the pool", e);
			}
	}
	
}
