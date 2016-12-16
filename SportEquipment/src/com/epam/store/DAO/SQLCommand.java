package com.epam.store.DAO;

public final class SQLCommand {
	public static final String SELECT_FROM_EQUIPMENT = "SELECT * FROM equipment";
	public static final String SELECT_FROM_CLIENT = "SELECT * FROM client";
	public static final String SELECT_FROM_RENT = "SELECT * FROM rent";
	
	public static final String INSERT_CLIENT = "INSERT INTO client (c_name, c_surname) VALUES (?,?)";
	public static final String INSERT_EQUIPMENT = "INSERT INTO equipment (e_category, e_title, e_price, e_quantity) VALUE (?,?,?,?)";
	
	
}
