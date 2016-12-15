package com.epam.store.DAO;

import java.util.ArrayList;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Equipment;

public interface InitializationDAO {
	Response initDAO() throws DAOException;
	ArrayList<Equipment> initStore() throws DAOException;
}
