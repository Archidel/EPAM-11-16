package com.epam.store.DAO;

import java.util.ArrayList;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.SportEquipment;

public interface InitializationDAO {
	Response initDAO() throws DAOException;
	ArrayList<SportEquipment> initStore() throws DAOException;
}
