package com.epam.store.DAO;

public final class SQLCommand {
	//General select
	public static final String SELECT_FROM_EQUIPMENT = "SELECT * FROM equipment";
	public static final String SELECT_FROM_CLIENT = "SELECT * FROM client";
	public static final String SELECT_FROM_RENT = "SELECT * FROM rent";
	//Select (more)
	public static final String SELECT_ID_TITLE_FROM_EQUIPMENT = "SELECT e_id, e_title FROM equipment";
	public static final String SELECT_NAME_SURNAME_FROM_CLIENT = "SELECT c_name, c_surname FROM client";
	public static final String SELECT_ID_NAME_SURNAME_FROM_CLIENT = "SELECT c_id, c_name, c_surname FROM client";
	public static final String SELECT_IDCLIENT_STATUS_FROM_RENT = "SELECT c_id, r_status FROM RENT";
	public static final String SELECT_TITLE_FROM_EQUIPMENT = "SELECT e_title FROM equipment";
	public static final String SELECT_IDEQUIPMENT_IDCLIENT_STATUS_FROM_RENT = "SELECT e_id, c_id, r_status FROM rent";
	//Insert
	public static final String INSERT_CLIENT = "INSERT INTO client (c_name, c_surname) VALUES (?,?)";
	public static final String INSERT_EQUIPMENT = "INSERT INTO equipment (e_category, e_title, e_price, e_quantity) VALUE (?,?,?,?)";
	public static final String INSERT_RENT_EQUIPMENT = "INSERT INTO rent (c_id, e_id, r_date_from, r_date_to, r_total_price) VALUE (?,?,?,?,?)";
	//update
	public static final String UPDATE_QUANTITY_FROM_EQUIPMENT = "UPDATE equipment SET e_quantity = e_quantity + ? WHERE e_id = ?";
	public static final String UPDATE_QUANTITY_INCREMENT_FROM_EQUIPMENT = "UPDATE equipment SET e_quantity = e_quantity + 1 WHERE e_id = ?";
	public static final String UPDATE_QUANTITY_DECREMENT_FROM_EQUIPMENT = "UPDATE equipment SET e_quantity = e_quantity - 1 WHERE e_id = ?";
	public static final String UPDATE_CHANGE_STATUS_FROM_RENT = "UPDATE rent SET r_status = ? WHERE c_id = ?";
	
}
