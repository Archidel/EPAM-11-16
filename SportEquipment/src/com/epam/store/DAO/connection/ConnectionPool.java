package com.epam.store.DAO.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.epam.store.DAO.exception.DAOException;

public class ConnectionPool implements Closeable{
	private static final ConnectionPool instance = new ConnectionPool();
		
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;
	
	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;
		
	private ConnectionPool() {
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);
		
		try{
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));	
		}catch(NumberFormatException e){
			poolsize = 6;
		}
	}

	public void init() throws SQLException{
			
		freeConnection = new ArrayBlockingQueue<>(poolsize);
		busyConnection = new ArrayBlockingQueue<>(poolsize);
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < poolsize; i++){
			freeConnection.add(DriverManager.getConnection(url, user, password));
		}
	}
	
	public Connection take() throws InterruptedException{
		Connection con = freeConnection.take();
		busyConnection.put(con);
		return con;
	}
	
	public void free(Connection con) throws InterruptedException, DAOException{
		if(con == null){
			throw new DAOException("Connection is null");
		}
		
		Connection tempConnection = con;
		con = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}

	@Override
	public void close() throws IOException {
		ArrayList<Connection> listConnection = new ArrayList<Connection>();		
		
		listConnection.addAll(busyConnection);
		listConnection.addAll(freeConnection);
	
		for(Connection connection : listConnection){
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}