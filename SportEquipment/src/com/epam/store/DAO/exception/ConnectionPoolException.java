package com.epam.store.DAO.exception;

public class ConnectionPoolException extends Exception{
	private static final long serialVersionUID = -5758616288009268571L;
	
	public ConnectionPoolException() {
		super();
	}
	
	public ConnectionPoolException(String message) {
		super(message);
	}
	
	public ConnectionPoolException(Exception e) {
		super(e);
	}
	
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
