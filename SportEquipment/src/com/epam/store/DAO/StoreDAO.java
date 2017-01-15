package com.epam.store.DAO;

import java.util.ArrayList;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;

public interface StoreDAO {
	
	Response addNewClient(String name, String surname) throws DAOException;
	Response addNewEquipment(Equipment equipment) throws DAOException;
	Response getEquipmentList() throws DAOException;
	
	boolean existClientInDatabase(String name, String surname) throws DAOException;
	int getAmountRentedEquipment(int idClient) throws DAOException;
	int getAmountRentedEquipment(int idClient, int idEquipment) throws DAOException;
	int getIdClient(String name, String surname) throws DAOException;
	int getIdEquipment(String titleEquipment) throws DAOException;
	int getPriceEquipment(String titleEquipment) throws DAOException;
	int getQuantityEquipment(String titleEquipment) throws DAOException;
	
	ArrayList<Rent> getRentList() throws DAOException;
	ArrayList<Client> getClientRentList(ArrayList<Rent> list) throws DAOException;
	ArrayList<Equipment> getEquipmentRentList(ArrayList<Rent> list) throws DAOException;

}
