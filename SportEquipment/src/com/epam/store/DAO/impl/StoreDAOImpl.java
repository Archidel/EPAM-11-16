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
import com.epam.store.bean.entity.Equipment;
import com.epam.store.controller.logging.StoreLogger;

public class StoreDAOImpl implements StoreDAO {

	@Override
	public Response addNewClient(String name, String surname) throws DAOException {
		Response response = new Response();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = pool.take();
			
			preparedStatement = con.prepareStatement(SQLCommand.INSERT_CLIENT);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.executeUpdate();
	
			response.setMessage("Client " + surname + " " + name + " was added in register");			
			
		} catch (InterruptedException e) {
			response.setErrorMessage("Trying to add a client failed");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			throw new DAOException(e);
		}finally{
			close(pool, con, null, preparedStatement, null);
		}
		
		return response;
	}

	@Override
	public Response addNewEquipment(Equipment equipment) throws DAOException {
		Response response = new Response();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		ResultSet resultSet = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_ID_TITLE_FROM_EQUIPMENT);
		
			//	Провека на существование такого снаряжения в базе данных. Если есть то записываем его id.
			int idExistingEquipment = 0;
			
			while(resultSet.next()){
				if(resultSet.getString(2).equalsIgnoreCase(equipment.getTitle())){
					idExistingEquipment = resultSet.getInt(1);
				}
			}
			
			//В зависимости от значения мы провидит операции дабвления или рбновления данных
			if(idExistingEquipment != 0){
				preparedStatement = con.prepareStatement(SQLCommand.UPDATE_QUANTITY_FROM_EQUIPMENT);
				preparedStatement.setInt(1, equipment.getQuantity());
				preparedStatement.setInt(2, idExistingEquipment);
			}else{
				preparedStatement = con.prepareStatement(SQLCommand.INSERT_EQUIPMENT);
				preparedStatement.setString(1, equipment.getCategory());
				preparedStatement.setString(2, equipment.getTitle());
				preparedStatement.setInt(3, equipment.getPrice());
				preparedStatement.setInt(4, equipment.getQuantity());
			}
			
			preparedStatement.executeUpdate();
			response.setMessage("Equipment was added in store equipment");
			
		} catch (InterruptedException e) {
			response.setMessage("Trying to add a equipment failed");
			response.setStatusError(true);
			throw new DAOException(e);
		} catch (SQLException e) {
			response.setErrorMessage("Error database query");
			response.setStatusError(true);
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, preparedStatement, resultSet);
		}
		
		return response;
	}

	@Override
	public Response getEquipmentList() throws DAOException {
		ListResponse listResponse = null;
		ArrayList<Equipment> list = null;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_EQUIPMENT);
			list = new ArrayList<Equipment>();
			
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
			listResponse.setMessage("List equipment was added");
			listResponse.setList(list);

		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return listResponse;
	}

	@Override
	public Response getRentList() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existClientInDatabase(String name, String surname) throws DAOException {
		boolean existClient = false;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_NAME_SURNAME_FROM_CLIENT);
			
			while(resultSet.next()){
				if((resultSet.getString(1).equalsIgnoreCase(name)) && (resultSet.getString(2).equalsIgnoreCase(surname))){
					existClient = true;
					break;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return existClient;
	}

	@Override
	public int getAmountRentedEquipment(int idClient) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int countRentedEquipment = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_IDCLIENT_STATUS_FROM_RENT);
			
			while(resultSet.next()){
				if((resultSet.getInt(1) == idClient) && (resultSet.getBoolean(2) == false)){
					countRentedEquipment++;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return countRentedEquipment;
	}

	@Override
	public int getAmountRentedEquipment(int idClient, int idEquipment) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int countRentedEquipment = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_IDEQUIPMENT_IDCLIENT_STATUS_FROM_RENT);
			
			while(resultSet.next()){
				if((resultSet.getInt(1) == idEquipment) && (resultSet.getInt(2) == idClient) && (resultSet.getBoolean(3) == false)){
					countRentedEquipment++;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return countRentedEquipment;
	}
	
	@Override
	public int getIdClient(String name, String surname) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int idClient = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_ID_NAME_SURNAME_FROM_CLIENT);
		
			while(resultSet.next()){
				if((resultSet.getString(2).equalsIgnoreCase(name)) && (resultSet.getString(3).equalsIgnoreCase(surname))){
					idClient = resultSet.getInt(1);
					break;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return idClient;
	}
	
	public int getPriceEquipment(String titleEquipment) throws DAOException{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int priceEquipment = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_TITLE_PRICE_FROM_EQUIPMENT);
			
			while(resultSet.next()){
				if((resultSet.getString(1).equalsIgnoreCase(titleEquipment))){
					priceEquipment = resultSet.getInt(2);
					break;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
	
		return priceEquipment;
	}
	
	@Override
	public int getQuantityEquipment(String titleEquipment) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int quantityEquipment = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_TITLE_QUANTITY_FROM_EQUIPMENT);
			
			while(resultSet.next()){
				if((resultSet.getString(1).equalsIgnoreCase(titleEquipment))){
					quantityEquipment = resultSet.getInt(2);
					break;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
		
		return quantityEquipment;
	}
	
	@Override
	public int getIdEquipment(String titleEquipment) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int idEquipment = 0;
		
		try {
			con = pool.take();
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_TITLE_IDEQUIPMENT_FROM_EQUIPMENT);
			
			while(resultSet.next()){
				if((resultSet.getString(1).equalsIgnoreCase(titleEquipment))){
					idEquipment = resultSet.getInt(2);
					break;
				}
			}
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			close(pool, con, statement, null, resultSet);
		}
	
		return idEquipment;
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
				StoreLogger.getLog().error("PrepareStatement isn't closed", e);
			}
		
		try { 
			if (pool != null) pool.free(con); 
			} catch (InterruptedException | DAOException e) {
				StoreLogger.getLog().error("Connection isn't return to the pool", e);
			}
	}



}