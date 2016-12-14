package com.epam.store.DAO.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.store.DAO.InitializationDAO;
import com.epam.store.DAO.SQLCommand;
import com.epam.store.DAO.connection.ConnectionPool;
import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.entity.Category;
import com.epam.store.bean.entity.Response;
import com.epam.store.bean.entity.SportEquipment;

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

	@Override
	public ArrayList<SportEquipment> initStore() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		ArrayList<SportEquipment> list = null;
		SportEquipment equipment = null;
		try {
			con = connectionPool.take();
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}

		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(SQLCommand.SELECT_FROM_SPORTEQUIPMENT);
			list = new ArrayList<SportEquipment>();
			
			while(resultSet.next()){
				equipment = new SportEquipment();
				equipment.setId(resultSet.getInt(1));
				Category category = new Category(resultSet.getString(2));
				equipment.setCategory(category);
				equipment.setTitle(resultSet.getString(3));
				int price  = Integer.parseInt(resultSet.getString(4));
				equipment.setPrice(price);
				
				list.add(equipment);
			}
			connectionPool.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
