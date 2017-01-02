package com.epam.store.DAO;

public final class SQLCommand {
	//General select
	public static final String SELECT_FROM_EQUIPMENT = "SELECT * FROM equipment";
	public static final String SELECT_FROM_CLIENT = "SELECT * FROM client";
	public static final String SELECT_FROM_RENT = "SELECT * FROM rent";
	//Select (more)
	public static final String SELECT_ID_TITLE_FROM_EQUIPMENT = "SELECT e_id, e_title FROM equipment";
	
	//Insert
	public static final String INSERT_CLIENT = "INSERT INTO client (c_name, c_surname) VALUES (?,?)";
	public static final String INSERT_EQUIPMENT = "INSERT INTO equipment (e_category, e_title, e_price, e_quantity) VALUE (?,?,?,?)";
	//update
	public static final String UPDATE_QUANTITY_FROM_EQUIPMENT = "UPDATE equipment SET e_quantity = e_quantity + ? WHERE e_id = ?";

}
