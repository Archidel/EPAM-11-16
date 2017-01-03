package com.epam.store.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.store.DAO.ClientDAO;
import com.epam.store.DAO.SQLCommand;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.DAO.connection.DBParameter;
import com.epam.store.DAO.connection.DBResourceManager;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.EquipmentResponse;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public Response RentEquipment(RentEquipmentRequest rentEquipmentRequest) throws DAOException {
		EquipmentResponse equipmentResponse = new EquipmentResponse();
		
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		int amountOfEquipment = Integer.parseInt(resourceManager.getValue(DBParameter.DB_AMOUNT_OF_EQUIPMENT)); //the amount of equipment that can be rented
		
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
			
			resultSet = statement.executeQuery(SQLCommand.SELECT_ID_NAME_SURNAME_FROM_CLIENT);
			int idClient = 0;
			while(resultSet.next()){
				if(resultSet.getString(2).equalsIgnoreCase(client.getName()) && resultSet.getString(3).equalsIgnoreCase(client.getSurname())){
					idClient = resultSet.getInt(1);
				}
			}
			resultSet.close();
			
			int numberOfAlreadyRented = 0;
			if(idClient != 0){
				resultSet = statement.executeQuery(SQLCommand.SELECT_IDCLIENT_FROM_RENT);
				while(resultSet.next()){
					if(resultSet.getInt(1) == idClient){
						if(resultSet.getBoolean(7) == false){
							numberOfAlreadyRented++;	
						}			
					}
				}
				resultSet.close();
				
				if(numberOfAlreadyRented < amountOfEquipment){
					resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_EQUIPMENT);
					preparedStatement = con.prepareStatement(SQLCommand.INSERT_RENT_EQUIPMENT);
							
					while(resultSet.next()){
						if(resultSet.getString(3).equalsIgnoreCase(titleEquipment)){
							Equipment equipment = new Equipment();
							equipment.setId(resultSet.getInt(1));
							equipment.setTitle(resultSet.getString(3));
							equipment.setCategory(resultSet.getString(2));
							equipment.setPrice(resultSet.getInt(4));
							equipment.setQuantity(resultSet.getInt(5));
							
							equipmentResponse.setEquipment(equipment);
							equipmentResponse.setMessage("Equipment "+ equipment.getTitle() +" was rented by " + client.getName() + " " + client.getSurname());
							
							preparedStatement.setInt(1, idClient);
							preparedStatement.setInt(2, equipment.getId());
							preparedStatement.setString(3, rentEquipment.getDateFrom());
							preparedStatement.setString(4, rentEquipment.getDateTo());
							preparedStatement.setInt(5, 1); //Значение по умолчанию!!! НЕ СДЕЛАН расчёт общей цены на арену (сделать на слое service)
							preparedStatement.executeUpdate();
							preparedStatement.close();
							
							preparedStatement = con.prepareStatement(SQLCommand.UPDATE_EUANTITY_DECREMENT_FROM_EQUIPMENT);
							preparedStatement.setInt(1, equipment.getId());
							preparedStatement.executeUpdate();
							
							break;
						}
					}
				}else{
					equipmentResponse.setErrorMessage("The client has rented maximum amount of equipment");
					equipmentResponse.setStatusError(true);
				}
			}else{
				equipmentResponse.setErrorMessage("This client is not registered in the database");
				equipmentResponse.setStatusError(true);
			}
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

	private void close(ConnectionPool pool, Connection con, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet){
		try { if (resultSet != null) resultSet.close(); } catch (SQLException e) {};
		try { if (statement != null) statement.close(); } catch (SQLException e) {};
		try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException e) {};
		try { if (pool != null) pool.free(con); } catch (InterruptedException | DAOException e) {}
	}
	
}
