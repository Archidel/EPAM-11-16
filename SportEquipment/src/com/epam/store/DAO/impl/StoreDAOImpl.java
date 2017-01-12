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
	public Response addNewClient(Client client) throws DAOException {
		Response response = new Response();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = pool.take();
			
			preparedStatement = con.prepareStatement(SQLCommand.INSERT_CLIENT);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.executeUpdate();
	
			response.setMessage("Client was added in register");			
			
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
			response.setMessage("Equipment was added in store");
			
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
		ArrayList<Equipment> list = new ArrayList<Equipment>();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			con = pool.take();
			
			statement = con.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_EQUIPMENT);
			
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
			
			if(list.size() == 0){
				listResponse = new ListResponse();
				listResponse.setMessage("List is empty");
			}else{
				listResponse = new ListResponse();
				listResponse.setList(list);
				listResponse.setMessage("Equipment list was added");
			}
			
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

	private void close(ConnectionPool pool, Connection con, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet){
		try { if (resultSet != null) resultSet.close(); } catch (SQLException e) {};
		try { if (statement != null) statement.close(); } catch (SQLException e) {};
		try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException e) {};
		try { if (pool != null) pool.free(con); } catch (InterruptedException | DAOException e) {}
	}
	
}